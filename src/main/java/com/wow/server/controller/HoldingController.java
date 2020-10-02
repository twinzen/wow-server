package com.wow.server.controller;

import com.wow.server.data.model.Holding;
import com.wow.server.data.model.Product;
import com.wow.server.data.model.User;
import com.wow.server.data.repository.HoldingRepository;
import com.wow.server.data.repository.ProductRepository;
import com.wow.server.data.repository.UserRepository;
import com.wow.server.dto.HoldingDTO;
import com.wow.server.dto.ProductDTO;
import com.wow.server.exception.DataNotFoundException;
import com.wow.server.mapper.HoldingMapper;
import com.wow.server.mapper.ProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/holdings/")
@Tag(name = "Holdings API")
public class HoldingController {

    private final UserRepository userRepository;
    private final HoldingRepository holdingRepository;
    private final ProductRepository productRepository;
    private final HoldingMapper holdingMapper;
    private final ProductMapper productMapper;

    @Autowired
    public HoldingController(
            UserRepository userRepository,
            HoldingRepository holdingRepository,
            ProductRepository productRepository,
            HoldingMapper holdingMapper,
            ProductMapper productMapper) {
        this.userRepository = userRepository;
        this.holdingRepository = holdingRepository;
        this.productRepository = productRepository;
        this.holdingMapper = holdingMapper;
        this.productMapper = productMapper;
    }

    @GetMapping("/{userId:\\d+}")
    @Operation(summary = "Returns HoldingsDTO list for given user ID")
    public List<HoldingDTO> getHoldingDTOsByUserId(@PathVariable(name = "userId") Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            String message = String.format("User with id %d doesn't exist", userId);
            log.error(message);
            throw new DataNotFoundException(message);
        }

        List<Holding> holdingList = holdingRepository.findAllByUserId(userId);
        Set<Long> productIdList = holdingList.stream()
                .map(Holding::getProductId)
                .collect(Collectors.toSet());

        if (productIdList.isEmpty()) {
            return Collections.emptyList();
        }

        List<Product> productList = productRepository.findAllByProductIdIn(productIdList);

        if (productList.isEmpty()) {
            return (Collections.emptyList());
        }

        List<HoldingDTO> holdingDTOList = holdingMapper.toHoldingDTOs(holdingList);
        Map<Long, ProductDTO> productDTOMap = productMapper.toProductDTOs(productList).stream()
                .collect(Collectors.toMap(ProductDTO::getProductId, Function.identity()));

        holdingDTOList.forEach(holdingDTO -> holdingDTO.setProduct(productDTOMap.get(holdingDTO.getProductId())));

        return holdingDTOList;
    }

}

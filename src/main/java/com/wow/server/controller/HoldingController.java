package com.wow.server.controller;

import com.wow.server.data.model.Holding;
import com.wow.server.data.model.Product;
import com.wow.server.data.repository.HoldingRepository;
import com.wow.server.data.repository.ProductRepository;
import com.wow.server.dto.HoldingDTO;
import com.wow.server.dto.ProductDTO;
import com.wow.server.exception.DataNotFoundException;
import com.wow.server.mapper.HoldingMapper;
import com.wow.server.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/holdings/")
public class HoldingController {

    private final HoldingRepository holdingRepository;
    private final ProductRepository productRepository;
    private final HoldingMapper holdingMapper;
    private final ProductMapper productMapper;

    @Autowired
    public HoldingController(
            HoldingRepository holdingRepository,
            ProductRepository productRepository,
            HoldingMapper holdingMapper,
            ProductMapper productMapper) {
        this.holdingRepository = holdingRepository;
        this.productRepository = productRepository;
        this.holdingMapper = holdingMapper;
        this.productMapper = productMapper;
    }

    @GetMapping("/{userId:\\d+}")
    public ResponseEntity<List<HoldingDTO>> getHoldingsForUser(@PathVariable(name = "userId") Long userId) {
        List<Holding> holdingList = holdingRepository.findAllByUserId(userId);
        Set<Long> productIdList = holdingList.stream()
                .map(Holding::getProductId)
                .collect(Collectors.toSet());

        if (productIdList.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<Product> productList = productRepository.findAllByProductIdIn(productIdList);

        if (holdingList.isEmpty() && productList.isEmpty()) {
            throw new DataNotFoundException("Can't find the holdings for given userId");
        }

        List<HoldingDTO> holdingDTOList = holdingMapper.toHoldingDTOs(holdingList);
        Map<Long, ProductDTO> productDTOMap = productMapper.toProductDTOs(productList).stream()
                .collect(Collectors.toMap(ProductDTO::getProductId, Function.identity()));

        holdingDTOList.forEach(holdingDTO -> holdingDTO.setProduct(productDTOMap.get(holdingDTO.getProductId())));

        return ResponseEntity.ok(holdingDTOList);
    }

}

package com.wow.server.api;

import com.wow.server.exception.DataNotFoundException;
import com.wow.server.holding.HoldingDTO;
import com.wow.server.holding.HoldingEntity;
import com.wow.server.holding.HoldingMapper;
import com.wow.server.holding.HoldingRepository;
import com.wow.server.product.ProductDTO;
import com.wow.server.product.ProductEntity;
import com.wow.server.product.ProductMapper;
import com.wow.server.product.ProductRepository;
import com.wow.server.user.UserEntity;
import com.wow.server.user.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/holdings")
@Tag(name = "Holdings API")
@RequiredArgsConstructor
public class HoldingController {

    private final UserRepository userRepository;
    private final HoldingRepository holdingRepository;
    private final ProductRepository productRepository;
    private final HoldingMapper holdingMapper;
    private final ProductMapper productMapper;

    @GetMapping("/{userId:\\d+}")
    @Operation(summary = "Returns Holding list for given user ID")
    public List<HoldingDTO> getHoldingsByUserId(
            @PathVariable(name = "userId") Long userId
    ) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            String message = String.format("User with id %d doesn't exist", userId);
            log.error(message);
            throw new DataNotFoundException(message);
        }

        List<HoldingEntity> holdingEntityList = holdingRepository.findAllByUserId(userId);
        Set<Long> productIdList = holdingEntityList.stream()
                .map(HoldingEntity::getProductId)
                .collect(Collectors.toSet());

        if (productIdList.isEmpty()) {
            return Collections.emptyList();
        }

        List<ProductEntity> productEntityList = productRepository.findAllByProductIdIn(productIdList);
        if (productEntityList.isEmpty() && !holdingEntityList.isEmpty()) {
            String message = String.format(
                    "Can't find Products for following productIds: %s", productIdList.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(",")));
            throw new IllegalStateException(message);
        }

        List<HoldingDTO> holdingDTOList = holdingMapper.toHoldingDTOs(holdingEntityList);
        Map<Long, ProductDTO> productDTOMap = productMapper.toProductDTOs(productEntityList).stream()
                .collect(Collectors.toMap(ProductDTO::getProductId, Function.identity()));

        holdingDTOList.forEach(holdingDTO -> holdingDTO.setProduct(productDTOMap.get(holdingDTO.getProductId())));

        return holdingDTOList;
    }

}

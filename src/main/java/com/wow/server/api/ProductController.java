package com.wow.server.api;

import com.wow.server.exception.BadRequestException;
import com.wow.server.product.ProductDTO;
import com.wow.server.product.ProductMapper;
import com.wow.server.product.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/products")
@Tag(name = "Get Products API")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @PostMapping
    @Operation(summary = "Accepts list of Product IDs in request body and returns Products list")
    public List<ProductDTO> getProductsByIds(
            @RequestBody Set<Long> productIds
    ) {
        if (productIds == null || productIds.isEmpty()) {
            String message = "List of productIds is empty";
            log.error(message);
            throw new BadRequestException(message);
        }

        return productMapper.toProductDTOs(productRepository.findAllByProductIdIn(productIds));
    }

}

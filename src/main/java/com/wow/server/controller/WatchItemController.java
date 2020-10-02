package com.wow.server.controller;

import com.wow.server.data.model.Product;
import com.wow.server.data.model.User;
import com.wow.server.data.model.WatchItem;
import com.wow.server.data.repository.ProductRepository;
import com.wow.server.data.repository.UserRepository;
import com.wow.server.data.repository.WatchItemRepository;
import com.wow.server.dto.ProductDTO;
import com.wow.server.dto.WatchItemDTO;
import com.wow.server.exception.DataNotFoundException;
import com.wow.server.mapper.ProductMapper;
import com.wow.server.mapper.WatchItemMapper;
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
@RequestMapping("/api/watchitems")
@Tag(name = "Watch Items API")
public class WatchItemController {

    private final UserRepository userRepository;
    private final WatchItemRepository watchItemRepository;
    private final ProductRepository productRepository;
    private final WatchItemMapper watchItemMapper;
    private final ProductMapper productMapper;

    @Autowired
    public WatchItemController(
            UserRepository userRepository,
            WatchItemRepository watchItemRepository,
            ProductRepository productRepository,
            WatchItemMapper watchItemMapper,
            ProductMapper productMapper) {
        this.userRepository = userRepository;
        this.watchItemRepository = watchItemRepository;
        this.productRepository = productRepository;
        this.watchItemMapper = watchItemMapper;
        this.productMapper = productMapper;
    }

    @GetMapping("/{userId:\\d+}")
    @Operation(summary = "Returns WatchItem list for given user ID")
    public List<WatchItemDTO> getWatchItemsByUserId(@PathVariable(name = "userId") Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            String message = String.format("User with id %d doesn't exist", userId);
            log.error(message);
            throw new DataNotFoundException(message);
        }

        List<WatchItem> watchItemList = watchItemRepository.findAllByUserId(userId);
        Set<Long> productIdList = watchItemList.stream()
                .map(WatchItem::getProductId)
                .collect(Collectors.toSet());

        if (productIdList.isEmpty()) {
            return Collections.emptyList();
        }

        List<Product> productList = productRepository.findAllByProductIdIn(productIdList);
        if (productList.isEmpty() && !watchItemList.isEmpty()) {
            String message = String.format(
                    "Can't find Products for following productIds: %s", productIdList.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(",")));
            throw new IllegalStateException(message);
        }

        List<WatchItemDTO> watchItemDTOList = watchItemMapper.toWatchItemDTOs(watchItemList);
        Map<Long, ProductDTO> productDTOMap = productMapper.toProductDTOs(productList).stream()
                .collect(Collectors.toMap(ProductDTO::getProductId, Function.identity()));

        watchItemDTOList.forEach(watchItemDTO ->
                watchItemDTO.setProduct(productDTOMap.get(watchItemDTO.getProductId())));

        return watchItemDTOList;
    }

}

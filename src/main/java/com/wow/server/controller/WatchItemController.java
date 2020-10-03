package com.wow.server.controller;

import com.wow.server.data.model.Product;
import com.wow.server.data.model.User;
import com.wow.server.watch_item.WatchItemEntity;
import com.wow.server.data.repository.ProductRepository;
import com.wow.server.data.repository.UserRepository;
import com.wow.server.watch_item.WatchItemRepository;
import com.wow.server.dto.ProductDTO;
import com.wow.server.watch_item.WatchItemDTO;
import com.wow.server.exception.DataNotFoundException;
import com.wow.server.mapper.ProductMapper;
import com.wow.server.watch_item.WatchItemMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

        getUser(userId);

        List<WatchItemEntity> watchItemEntityList = watchItemRepository.findAllByUserId(userId);
        Set<Long> productIdList = watchItemEntityList.stream()
                .map(WatchItemEntity::getProductId)
                .collect(Collectors.toSet());

        if (productIdList.isEmpty()) {
            return Collections.emptyList();
        }

        List<Product> productList = productRepository.findAllByProductIdIn(productIdList);
        if (productList.isEmpty() && !watchItemEntityList.isEmpty()) {
            String message = String.format(
                    "Can't find Products for following productIds: %s", productIdList.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(",")));
            throw new IllegalStateException(message);
        }

        List<WatchItemDTO> watchItemDTOList = watchItemMapper.toWatchItemDTOs(watchItemEntityList);
        Map<Long, ProductDTO> productDTOMap = productMapper.toProductDTOs(productList).stream()
                .collect(Collectors.toMap(ProductDTO::getProductId, Function.identity()));

        watchItemDTOList.forEach(watchItemDTO ->
                watchItemDTO.setProduct(productDTOMap.get(watchItemDTO.getProductId())));

        return watchItemDTOList;
    }

    @PostMapping("/{userId:\\d+}")
    @Operation(summary = "Add item to watch for user")
    public ResponseEntity<Void> addItemToWatch(
            @PathVariable(name = "userId") Long userId,
            @RequestParam(name = "productId") Long productId
    ) {
        getUser(userId);
        getProduct(productId);

        Optional<WatchItemEntity> existingWatchItemEntity = watchItemRepository.findByUserIdAndProductId(userId, productId);
        if (!existingWatchItemEntity.isPresent()) {
            log.info(String.format("Already watching product %d", productId));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        WatchItemEntity newWatchItemEntity = new WatchItemEntity();
        newWatchItemEntity.setProductId(productId);
        newWatchItemEntity.setUserId(userId);
        newWatchItemEntity.setCreationDateTime(LocalDateTime.now());

        watchItemRepository.save(newWatchItemEntity);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{userId:\\d+}/watchedProduct/{productId:\\d+}")
    @Operation(summary = "Remove item to watch for user")
    public ResponseEntity<Void> removeItemToWatch(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "productId") Long productId
    ) {
        getUser(userId);
        getProduct(productId);

        Optional<WatchItemEntity> watchItemEntity = watchItemRepository.findByUserIdAndProductId(userId, productId);
        if (!watchItemEntity.isPresent()) {
            log.info(String.format("Product with id %d not watched", productId));
            return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();
        }

        watchItemRepository.deleteById(watchItemEntity.get().getWatchItemId());

        return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();
    }

    private User getUser(
            Long userId
    ) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            String message = String.format("User with id %d doesn't exist", userId);
            log.error(message);
            throw new DataNotFoundException(message);
        }
        return user.get();
    }

    private Product getProduct(
            Long productId
    ) {
        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            String message = String.format("Product with id %d doesn't exist", productId);
            log.error(message);
            throw new DataNotFoundException(message);
        }
        return product.get();
    }


}

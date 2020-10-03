package com.wow.server.api;

import com.wow.server.product.ProductEntity;
import com.wow.server.product.ProductRepository;
import com.wow.server.user.UserRepository;
import com.wow.server.product.ProductDTO;
import com.wow.server.exception.DataNotFoundException;
import com.wow.server.product.ProductMapper;
import com.wow.server.user.UserEntity;
import com.wow.server.watchitem.WatchItemDTO;
import com.wow.server.watchitem.WatchItemEntity;
import com.wow.server.watchitem.WatchItemMapper;
import com.wow.server.watchitem.WatchItemRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/watchitems")
@Tag(name = "Watch Items API")
@RequiredArgsConstructor
public class WatchItemController {

    private final UserRepository userRepository;
    private final WatchItemRepository watchItemRepository;
    private final ProductRepository productRepository;
    private final WatchItemMapper watchItemMapper;
    private final ProductMapper productMapper;


    @GetMapping("/{userId:\\d+}")
    @Operation(summary = "Returns WatchItem list for given user ID")
    public List<WatchItemDTO> getWatchItemsByUserId(@PathVariable(name = "userId") Long userId) {

        getUserEntity(userId);

        List<WatchItemEntity> watchItemEntityList = watchItemRepository.findAllByUserId(userId);
        Set<Long> productIdList = watchItemEntityList.stream()
                .map(WatchItemEntity::getProductId)
                .collect(Collectors.toSet());

        if (productIdList.isEmpty()) {
            return Collections.emptyList();
        }

        List<ProductEntity> productEntityList = productRepository.findAllByProductIdIn(productIdList);
        if (productEntityList.isEmpty() && !watchItemEntityList.isEmpty()) {
            String message = String.format(
                    "Can't find Products for following productIds: %s", productIdList.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(",")));
            throw new IllegalStateException(message);
        }

        List<WatchItemDTO> watchItemDTOList = watchItemMapper.toWatchItemDTOs(watchItemEntityList);
        Map<Long, ProductDTO> productDTOMap = productMapper.toProductDTOs(productEntityList).stream()
                .collect(Collectors.toMap(ProductDTO::getProductId, Function.identity()));

        watchItemDTOList.forEach(watchItemDTO ->
                watchItemDTO.setProduct(productDTOMap.get(watchItemDTO.getProductId())));

        return watchItemDTOList;
    }





    @PostMapping("/{userId:\\d+}")
    @Operation(summary = "Add item to watch for user")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItemToWatch(
            @PathVariable(name = "userId") Long userId,
            @RequestParam(name = "productId") Long productId
    ) {
        getUserEntity(userId);
        getProduct(productId);

        Optional<WatchItemEntity> existingWatchItemEntity = watchItemRepository.findByUserIdAndProductId(userId, productId);
        if (!existingWatchItemEntity.isPresent()) {
            log.info(String.format("Already watching product %d", productId));
            return ;
        }

        WatchItemEntity newWatchItemEntity = new WatchItemEntity();
        newWatchItemEntity.setProductId(productId);
        newWatchItemEntity.setUserId(userId);
        newWatchItemEntity.setCreationDateTime(LocalDateTime.now());

        watchItemRepository.save(newWatchItemEntity);

    }

    @DeleteMapping("/{userId:\\d+}/watchedProduct/{productId:\\d+}")
    @Operation(summary = "Remove item to watch for user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeItemToWatch(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "productId") Long productId
    ) {
        getUserEntity(userId);
        getProduct(productId);

        Optional<WatchItemEntity> watchItemEntity = watchItemRepository.findByUserIdAndProductId(userId, productId);
        if (!watchItemEntity.isPresent()) {
            log.info(String.format("Product with id %d not watched", productId));
            return;
        }

        watchItemRepository.deleteById(watchItemEntity.get().getWatchItemId());

    }

    private UserEntity getUserEntity(
            Long userId
    ) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            String message = String.format("User with id %d doesn't exist", userId);
            log.error(message);
            throw new DataNotFoundException(message);
        }
        return user.get();
    }

    private ProductEntity getProduct(
            Long productId
    ) {
        Optional<ProductEntity> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            String message = String.format("Product with id %d doesn't exist", productId);
            log.error(message);
            throw new DataNotFoundException(message);
        }
        return product.get();
    }




}

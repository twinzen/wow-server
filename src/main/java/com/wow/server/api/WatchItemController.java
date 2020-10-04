package com.wow.server.api;

import com.wow.server.exception.DataNotFoundException;
import com.wow.server.product.ProductEntity;
import com.wow.server.product.ProductRepository;
import com.wow.server.user.UserEntity;
import com.wow.server.user.UserRepository;
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
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{userId:\\d+}")
    @Operation(summary = "Returns WatchItem list for given user ID")
    public List<WatchItemDTO> getWatchItemsByUserId(
            @PathVariable(name = "userId") Long userId
    ) {
        UserEntity userEntity = getUserEntity(userId);
        return watchItemMapper.toWatchItemDTOs(userEntity.getWatchItems());
    }

    @PostMapping("/{userId:\\d+}")
    @Operation(summary = "Add item to watch for user")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItemToWatch(
            @PathVariable(name = "userId") Long userId,
            @RequestParam(name = "productId") Long productId
    ) {
        UserEntity userEntity = getUserEntity(userId);
        ProductEntity productEntity = getProduct(productId);

        Optional<WatchItemEntity> existingWatchItemEntity = watchItemRepository.findByUserIdAndProductId(userId, productId);
        if (existingWatchItemEntity.isPresent()) {
            log.info(String.format("Already watching product %d", productId));
            return;
        }

        WatchItemEntity newWatchItemEntity = new WatchItemEntity();
        newWatchItemEntity.setProduct(productEntity);
        newWatchItemEntity.setCreationDateTime(LocalDateTime.now());
        userEntity.addWatchItem(newWatchItemEntity);
        userRepository.save(userEntity);
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

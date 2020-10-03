package com.wow.server;

import com.wow.server.data.model.Holding;
import com.wow.server.data.model.Product;
import com.wow.server.data.projection.UserMinimalProjection;
import com.wow.server.data.repository.HoldingRepository;
import com.wow.server.data.repository.ProductRepository;
import com.wow.server.data.repository.UserRepository;
import com.wow.server.user.UserEntity;
import com.wow.server.watchitem.WatchItemEntity;
import com.wow.server.watchitem.WatchItemRepository;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.mockito.Mockito.when;

public final class DataPreparationUtils {

    private DataPreparationUtils() {
    }

    public static void mockProductRepositoryResponse(
            Set<Long> productIds, ProductRepository productRepository) {
        List<Product> products = productIds.stream()
                .map(productId -> {
                    Product product = new Product();
                    product.setProductId(productId);
                    product.setProductName("Some product " + productId);
                    product.setProductCode(String.valueOf(productId));
                    return product;
                })
                .collect(Collectors.toList());
        when(productRepository.findAllByProductIdIn(productIds)).thenReturn(products);
    }

    public static void mockHoldingRepositoryResponse(
            long userId, List<Pair<Long, Long>> productIds, HoldingRepository holdingRepository) {
        List<Holding> holdings = productIds.stream()
                .map(holdingProductId -> {
                    Holding holding = new Holding();
                    holding.setUserId(userId);
                    holding.setHoldingId(holdingProductId.getLeft());
                    holding.setProductId(holdingProductId.getRight());
                    holding.setQuantity(BigDecimal.valueOf(holdingProductId.getRight() / 100));
                    holding.setCreationDateTime(LocalDateTime.now());
                    holding.setUpdateDateTime(LocalDateTime.now());
                    return holding;
                })
                .collect(Collectors.toList());
        when(holdingRepository.findAllByUserId(userId)).thenReturn(holdings);
    }

    public static void mockUserRepositoryResponse(long userId, UserRepository userRepository) {
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        user.setDisplayName("display-name-of-user" + userId);
        user.setUserName("user-name-of-user" + userId);
        user.setEmail(userId + "@wow.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    }

    public static void mockUserRepositoryResponse(int numberOfUsers, UserRepository userRepository) {
        when(userRepository.finAllMinimal()).thenReturn(LongStream.range(1, numberOfUsers + 1)
                .mapToObj(DataPreparationUtils::prepareMinimalUser)
                .collect(Collectors.toList()));
    }

    private static UserMinimalProjection prepareMinimalUser(long userId) {
        return new UserMinimalProjection(userId, "display-name-of-user" + userId);
    }

    public static void mockWatchItemRepositoryResponse(
            long userId, List<Pair<Long, Long>> productIds, WatchItemRepository watchItemRepository) {
        List<WatchItemEntity> watchItemEntities = productIds.stream()
                .map(holdingProductId -> {
                    WatchItemEntity watchItemEntity = new WatchItemEntity();
                    watchItemEntity.setUserId(userId);
                    watchItemEntity.setWatchItemId(holdingProductId.getLeft());
                    watchItemEntity.setProductId(holdingProductId.getRight());
                    watchItemEntity.setCreationDateTime(LocalDateTime.now());
                    watchItemEntity.setUpdateDateTime(LocalDateTime.now());
                    return watchItemEntity;
                })
                .collect(Collectors.toList());
        when(watchItemRepository.findAllByUserId(userId)).thenReturn(watchItemEntities);
    }
}

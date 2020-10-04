package com.wow.server;

import com.wow.server.holding.HoldingEntity;
import com.wow.server.product.ProductEntity;
import com.wow.server.product.ProductRepository;
import com.wow.server.user.UserEntity;
import com.wow.server.user.UserMinimalProjection;
import com.wow.server.user.UserRepository;
import com.wow.server.watchitem.WatchItemEntity;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.mockito.Mockito.when;

public final class DataPreparationUtils {

    private DataPreparationUtils() {
    }

    public static void mockProductRepositoryResponse(
            Set<Long> productIds, ProductRepository productRepository) {
        List<ProductEntity> productEntities = getProductEntities(productIds);
        when(productRepository.findAllByProductIdIn(productIds)).thenReturn(productEntities);
    }

    public static void mockUserRepositoryResponse(long userId, UserRepository userRepository) {
        UserEntity user = getUserEntity(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    }

    public static void mockUserRepositoryResponse(
            long userId, List<Pair<Long, Long>> productIds, UserRepository userRepository) {
        UserEntity user = getUserEntity(userId);
        List<HoldingEntity> holdingEntities = getHoldingEntities(userId, productIds);
        Map<Long, ProductEntity> productEntities = getProductEntities(productIds.stream()
                .map(Pair::getRight)
                .collect(Collectors.toSet())).stream()
                .collect(Collectors.toMap(ProductEntity::getProductId, Function.identity()));
        holdingEntities.forEach(holdingEntity -> {
            holdingEntity.setProduct(productEntities.get(holdingEntity.getProductId()));
            holdingEntity.setUser(user);
        });
        user.setHoldings(holdingEntities);

        List<WatchItemEntity> watchItemEntities = getWatchItemEntities(userId, productIds);
        watchItemEntities.forEach(watchItemEntity -> {
            watchItemEntity.setUser(user);
            watchItemEntity.setProduct(productEntities.get(watchItemEntity.getProductId()));
        });
        user.setWatchItems(watchItemEntities);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    }

    public static void mockUserRepositoryResponse(int numberOfUsers, UserRepository userRepository) {
        when(userRepository.finAllMinimal()).thenReturn(LongStream.range(1, numberOfUsers + 1)
                .mapToObj(DataPreparationUtils::prepareMinimalUser)
                .collect(Collectors.toList()));
    }

    private static UserEntity getUserEntity(long userId) {
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        user.setDisplayName("display-name-of-user" + userId);
        user.setUserName("user-name-of-user" + userId);
        user.setEmail(userId + "@wow.com");
        return user;
    }

    private static List<HoldingEntity> getHoldingEntities(long userId, List<Pair<Long, Long>> productIds) {
        return productIds.stream()
                .map(holdingProductId -> {
                    HoldingEntity holdingEntity = new HoldingEntity();
                    holdingEntity.setUserId(userId);
                    holdingEntity.setHoldingId(holdingProductId.getLeft());
                    holdingEntity.setProductId(holdingProductId.getRight());
                    holdingEntity.setQuantity(BigDecimal.valueOf(holdingProductId.getRight() / 100));
                    holdingEntity.setCreationDateTime(LocalDateTime.now());
                    holdingEntity.setUpdateDateTime(LocalDateTime.now());
                    return holdingEntity;
                })
                .collect(Collectors.toList());
    }

    private static List<ProductEntity> getProductEntities(Set<Long> productIds) {
        return productIds.stream()
                .map(productId -> {
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setProductId(productId);
                    productEntity.setProductName("Some product " + productId);
                    productEntity.setProductCode(String.valueOf(productId));
                    return productEntity;
                })
                .collect(Collectors.toList());
    }

    private static UserMinimalProjection prepareMinimalUser(long userId) {
        return new UserMinimalProjection(userId, "display-name-of-user" + userId);
    }

    private static List<WatchItemEntity> getWatchItemEntities(long userId, List<Pair<Long, Long>> productIds) {
        return productIds.stream()
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
    }
}

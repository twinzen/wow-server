package com.wow.server;

import com.wow.server.data.model.Holding;
import com.wow.server.data.model.Product;
import com.wow.server.data.model.User;
import com.wow.server.data.model.WatchItem;
import com.wow.server.data.projection.UserMinimalProjection;
import com.wow.server.data.repository.HoldingRepository;
import com.wow.server.data.repository.ProductRepository;
import com.wow.server.data.repository.UserRepository;
import com.wow.server.data.repository.WatchItemRepository;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.mockito.Mockito.when;

public final class DataPreparationUtils {

    private DataPreparationUtils() {
    }

    public static void mockProductRepositoryResponse(
            List<Pair<Long, Long>> productIds, ProductRepository productRepository) {
        List<Product> products = productIds.stream()
                .map(Pair::getRight)
                .map(productId -> {
                    Product product = new Product();
                    product.setProductId(productId);
                    product.setProductName("Some product " + productId);
                    product.setProductCode(String.valueOf(productId));
                    return product;
                })
                .collect(Collectors.toList());
        when(productRepository.findAllByProductIdIn(productIds.stream()
                .map(Pair::getRight)
                .collect(Collectors.toSet()))).thenReturn(products);
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
        User user = new User();
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
        List<WatchItem> watchItems = productIds.stream()
                .map(holdingProductId -> {
                    WatchItem watchItem = new WatchItem();
                    watchItem.setUserId(userId);
                    watchItem.setWatchItemId(holdingProductId.getLeft());
                    watchItem.setProductId(holdingProductId.getRight());
                    watchItem.setCreationDateTime(LocalDateTime.now());
                    watchItem.setUpdateDateTime(LocalDateTime.now());
                    return watchItem;
                })
                .collect(Collectors.toList());
        when(watchItemRepository.findAllByUserId(userId)).thenReturn(watchItems);
    }
}

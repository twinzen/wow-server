package com.wow.server.data.repository;

import com.google.common.collect.Sets;
import com.wow.server.product.ProductEntity;
import com.wow.server.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@DataJpaTest
@ActiveProfiles("test")
public class ProductEntityRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void existing_products_can_be_found_by_set_of_products() {
        // given
        List<ProductEntity> productEntities = createProducts(Sets.newHashSet(1L,2L,3L));

        // when
        List<ProductEntity> productEntityList = productRepository.findAllByProductIdIn(productEntities.stream()
                .map(ProductEntity::getProductId)
                .collect(Collectors.toSet()));

        // then
        Map<Long, ProductEntity> productMap = productEntities.stream()
                .collect(Collectors.toMap(ProductEntity::getProductId, Function.identity()));
        productEntityList.forEach(product -> {
            ProductEntity originalProductEntity = productMap.get(product.getProductId());
            assertThat(originalProductEntity, notNullValue());
            assertThat(originalProductEntity.getProductCode(), is(product.getProductCode()));
            assertThat(originalProductEntity.getProductName(), is(product.getProductName()));
        });
    }

    private List<ProductEntity> createProducts(Set<Long> ids) {
        return productRepository.saveAll(ids.stream()
                .map(id -> {
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setProductCode("product-code-" + id);
                    productEntity.setProductName("product-name-" + id);
                    return productEntity;
                })
                .collect(Collectors.toList()));
    }
}
package com.wow.server.data.repository;

import com.google.common.collect.Sets;
import com.wow.server.data.model.Product;
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
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void existing_products_can_be_found_by_set_of_products() {
        // given
        List<Product> products = createProducts(Sets.newHashSet(1L,2L,3L));

        // when
        List<Product> productList = productRepository.findAllByProductIdIn(products.stream()
                .map(Product::getProductId)
                .collect(Collectors.toSet()));

        // then
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductId, Function.identity()));
        productList.forEach(product -> {
            Product originalProduct = productMap.get(product.getProductId());
            assertThat(originalProduct, notNullValue());
            assertThat(originalProduct.getProductCode(), is(product.getProductCode()));
            assertThat(originalProduct.getProductName(), is(product.getProductName()));
        });
    }

    private List<Product> createProducts(Set<Long> ids) {
        return productRepository.saveAll(ids.stream()
                .map(id -> {
                    Product product = new Product();
                    product.setProductCode("product-code-" + id);
                    product.setProductName("product-name-" + id);
                    return product;
                })
                .collect(Collectors.toList()));
    }
}
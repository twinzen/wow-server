package com.wow.server.controller;

import com.google.common.io.Resources;
import com.wow.server.data.model.Holding;
import com.wow.server.data.model.Product;
import com.wow.server.data.model.User;
import com.wow.server.data.repository.HoldingRepository;
import com.wow.server.data.repository.ProductRepository;
import com.wow.server.data.repository.UserRepository;
import com.wow.server.mapper.HoldingMapperImpl;
import com.wow.server.mapper.ProductMapperImpl;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {HoldingController.class, HoldingMapperImpl.class, ProductMapperImpl.class})
@WebMvcTest
@ActiveProfiles("test")
public class HoldingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private HoldingRepository holdingRepository;
    @MockBean
    private ProductRepository productRepository;

    @Test
    public void endpointCanReturnDataSuccessfully() throws Exception {
        // given
        String expected = Resources.toString(
                Resources.getResource("messageBodies/holdingsGetByUserIdResponseSuccess.json"),
                StandardCharsets.UTF_8);
        long userId = 1L;
        List<Pair<Long, Long>> productIds = Lists.newArrayList(
                ImmutablePair.of(1L, 34567L),
                ImmutablePair.of(2L, 34568L));
        mockUserRepositoryResponse(userId);
        mockHoldingRepositoryResponse(userId, productIds);
        mockProductRepositoryResponse(productIds);

        // when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/holdings/{userid}", userId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        String resultDOW = result.getResponse().getContentAsString();
        assertNotNull(resultDOW);
        assertEquals(expected, resultDOW);
    }

    private void mockProductRepositoryResponse(List<Pair<Long, Long>> productIds) {
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

    private void mockHoldingRepositoryResponse(long userId, List<Pair<Long, Long>> productIds) {
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

    private void mockUserRepositoryResponse(long userId) {
        User user = new User();
        user.setUserId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    }

}
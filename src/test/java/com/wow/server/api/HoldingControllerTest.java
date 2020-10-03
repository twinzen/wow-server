package com.wow.server.api;

import com.wow.server.DataPreparationUtils;
import com.wow.server.holding.HoldingRepository;
import com.wow.server.product.ProductRepository;
import com.wow.server.user.UserRepository;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    public void endpoint_can_return_data_successfully() throws Exception {
        // given
        long userId = 1L;
        List<Pair<Long, Long>> productIds = Lists.newArrayList(
                ImmutablePair.of(1L, 34567L),
                ImmutablePair.of(2L, 34568L));
        DataPreparationUtils.mockUserRepositoryResponse(userId, userRepository);
        DataPreparationUtils.mockHoldingRepositoryResponse(userId, productIds, holdingRepository);
        DataPreparationUtils.mockProductRepositoryResponse(productIds.stream()
                .map(Pair::getRight)
                .collect(Collectors.toSet()), productRepository);

        // when
        ResultActions result = mockMvc.perform(get("/api/holdings/{userid}", userId)
                .accept(MediaType.APPLICATION_JSON));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].userId", contains(1, 1)))
                .andExpect(jsonPath("$[*].holdingId", everyItem(in(productIds.stream()
                        .map(Pair::getLeft)
                        .map(Long::intValue)
                        .collect(Collectors.toList())))))
                .andExpect(jsonPath("$[*].product.productId", everyItem(in(productIds.stream()
                        .map(Pair::getRight)
                        .map(Long::intValue)
                        .collect(Collectors.toList())))));
    }

}
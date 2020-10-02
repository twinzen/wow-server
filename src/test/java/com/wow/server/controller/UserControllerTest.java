package com.wow.server.controller;

import com.wow.server.DataPreparationUtils;
import com.wow.server.data.repository.UserRepository;
import com.wow.server.mapper.UserMapperImpl;
import com.wow.server.mapper.UserMinimalMapperImpl;
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

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {
        UserController.class,
        GlobalControllerExceptionHandler.class,
        UserMinimalMapperImpl.class,
        UserMapperImpl.class})
@WebMvcTest
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void endpoint_can_return_user_minimal_data_list_successfully() throws Exception {
        // given
        DataPreparationUtils.mockUserRepositoryResponse(9, userRepository);

        // when
        ResultActions result = mockMvc.perform(get("/api/users")
                .accept(MediaType.APPLICATION_JSON));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(9)))
                .andExpect(jsonPath("$[*].userId", everyItem(in(IntStream.range(1, 10)
                        .boxed()
                        .collect(Collectors.toList())))));
    }

    @Test
    public void endpoint_can_return_specific_user_data_successfully() throws Exception {
        // given
        long userId = 1000L;
        DataPreparationUtils.mockUserRepositoryResponse(userId, userRepository);

        // when
        ResultActions result = mockMvc.perform(get("/api/users/{userId}", userId)
                .accept(MediaType.APPLICATION_JSON));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", equalTo((int) userId)))
                .andExpect(jsonPath("$.displayName", is("display-name-of-user" + userId)))
                .andExpect(jsonPath("$.userName", is("user-name-of-user" + userId)))
                .andExpect(jsonPath("$.email", is(userId + "@wow.com")));
    }

    @Test
    public void endpoint_responds_with_404_error_in_case_where_data_requested_for_non_existing_user() throws Exception {
        // given
        long userId = 1000L;

        // when
        ResultActions result = mockMvc.perform(get("/api/users/{userId}", userId)
                .accept(MediaType.APPLICATION_JSON));

        // then
        result
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("User with ID 1000 does not exist")));
    }

    @Test
    public void endpoint_responds_with_500_error_in_case_of_thrown_unhandled_exception() throws Exception {
        // given
        when(userRepository.finAllMinimal()).thenThrow(new IllegalStateException("Failed to retrieve data from DB"));

        // when
        ResultActions result = mockMvc.perform(get("/api/users")
                .accept(MediaType.APPLICATION_JSON));

        // then
        result
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("Internal server error. Please contact system administrator.")));
    }
}
package com.wow.server.data.repository;

import com.wow.server.user.UserMinimalProjection;
import com.wow.server.user.UserEntity;
import com.wow.server.user.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class UserEntityRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void user_projection_can_be_constructed_for_existing_user() {
        // given
        int initialSize = (int) userRepository.count();
        String displayName = "test-user";
        Long userId = prepareUser(displayName);

        // when
        List<UserMinimalProjection> userProjections = userRepository.finAllMinimal();

        // then
        assertThat(userProjections, notNullValue());
        assertThat(userProjections, hasSize(initialSize + 1));
        assertThat(userProjections, hasItem(Matchers.<UserMinimalProjection>hasProperty(
                "userId", is(userId))));
        assertThat(userProjections, hasItem(Matchers.<UserMinimalProjection>hasProperty(
                "displayName", is(displayName))));
    }

    private Long prepareUser(String displayName) {
        UserEntity user = new UserEntity();
        user.setDisplayName(displayName);
        UserEntity savedUser = userRepository.saveAndFlush(user);
        assertNotNull(savedUser);

        assertEquals(displayName,savedUser.getDisplayName());
        return savedUser.getUserId();
    }

}
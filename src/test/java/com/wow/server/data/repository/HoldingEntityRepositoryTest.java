package com.wow.server.data.repository;

import com.wow.server.holding.HoldingEntity;
import com.wow.server.holding.HoldingRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class HoldingEntityRepositoryTest {

    @Autowired
    private HoldingRepository holdingRepository;

    @Test
    public void existing_holding_can_be_found_by_user_id() {
        // given
        long userId = 1L;
        HoldingEntity holdingEntity = new HoldingEntity();
        holdingEntity.setUserId(userId);
        HoldingEntity savedHoldingEntity = holdingRepository.save(holdingEntity);
        assertNotNull(savedHoldingEntity);
        assertNotNull(savedHoldingEntity.getHoldingId());

        // when
        List<HoldingEntity> holdingEntityList = holdingRepository.findAllByUserId(userId);

        // then
        assertThat(holdingEntityList, notNullValue());
        assertThat(holdingEntityList, hasSize(1));
        assertThat(holdingEntityList, hasItem(Matchers.<HoldingEntity>hasProperty("holdingId", is(savedHoldingEntity.getHoldingId()))));
    }

}
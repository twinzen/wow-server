package com.wow.server.data.repository;

import com.wow.server.data.model.Holding;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class HoldingRepositoryTest {

    @Autowired
    private HoldingRepository holdingRepository;

    @Test
    public void existing_holding_can_be_found_by_user_id() {
        // given
        long userId = 1L;
        Holding holding = new Holding();
        holding.setUserId(userId);
        Holding savedHolding = holdingRepository.save(holding);
        assertNotNull(savedHolding);
        assertNotNull(savedHolding.getHoldingId());

        // when
        List<Holding> holdingList = holdingRepository.findAllByUserId(userId);

        // then
        assertFalse(holdingList.isEmpty());
        assertEquals(savedHolding.getHoldingId(), holdingList.get(0).getHoldingId());
    }

}
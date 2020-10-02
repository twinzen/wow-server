package com.wow.server.data.repository;

import com.wow.server.data.model.WatchItem;
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
public class WatchItemRepositoryTest {

    @Autowired
    private WatchItemRepository watchItemRepository;

    @Test
    public void existing_watch_item_can_be_found_by_user_id() {
        // given
        long userId = 1L;
        WatchItem watchItem = new WatchItem();
        watchItem.setUserId(userId);
        watchItem.setProductId(100L);
        WatchItem savedWatchItem = watchItemRepository.save(watchItem);
        assertNotNull(savedWatchItem);
        assertNotNull(savedWatchItem.getWatchItemId());

        // when
        List<WatchItem> watchItems = watchItemRepository.findAllByUserId(userId);

        // then
        assertThat(watchItems, notNullValue());
        assertThat(watchItems, hasSize(1));
        assertThat(watchItems, hasItem(
                Matchers.<WatchItem>hasProperty("watchItemId", is(savedWatchItem.getWatchItemId()))));
    }
}
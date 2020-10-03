package com.wow.server.data.repository;

import com.wow.server.watchitem.WatchItemEntity;
import com.wow.server.watchitem.WatchItemRepository;
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
public class WatchItemEntityRepositoryTest {

    @Autowired
    private WatchItemRepository watchItemRepository;

    @Test
    public void existing_watch_item_can_be_found_by_user_id() {
        // given
        long userId = 999L;
        WatchItemEntity watchItemEntity = new WatchItemEntity();
        watchItemEntity.setUserId(userId);
        watchItemEntity.setProductId(100L);
        WatchItemEntity savedWatchItemEntity = watchItemRepository.save(watchItemEntity);
        assertNotNull(savedWatchItemEntity);
        assertNotNull(savedWatchItemEntity.getWatchItemId());

        // when
        List<WatchItemEntity> watchItemEntities = watchItemRepository.findAllByUserId(userId);

        // then
        assertThat(watchItemEntities, notNullValue());
        assertThat(watchItemEntities, hasSize(1));
        assertThat(watchItemEntities, hasItem(
                Matchers.<WatchItemEntity>hasProperty("watchItemId", is(savedWatchItemEntity.getWatchItemId()))));
    }
}
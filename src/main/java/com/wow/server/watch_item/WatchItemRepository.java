package com.wow.server.watch_item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchItemRepository extends JpaRepository<WatchItemEntity, Long>{

    List<WatchItemEntity> findAllByUserId(Long userId);


    Optional<WatchItemEntity> findByUserIdAndProductId(Long userId, Long productId);
}

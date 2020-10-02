package com.wow.server.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wow.server.data.model.WatchItem;

import java.util.List;

@Repository
public interface WatchItemRepository extends JpaRepository<WatchItem, Long>{

    List<WatchItem> findAllByUserId(Long userId);
}

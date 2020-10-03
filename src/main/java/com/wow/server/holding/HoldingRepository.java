package com.wow.server.holding;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoldingRepository extends JpaRepository<HoldingEntity, Long> {

    @Query
    List<HoldingEntity> findAllByUserId(Long userId);

}

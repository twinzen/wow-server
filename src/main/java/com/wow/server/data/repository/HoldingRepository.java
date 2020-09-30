package com.wow.server.data.repository;

import com.wow.server.data.model.Holding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoldingRepository extends JpaRepository<Holding, Long> {

    @Query
    List<Holding> findAllByUserId(Long userId);

}

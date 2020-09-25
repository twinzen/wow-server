package com.wow.server.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wow.server.data.model.Holding;

@Repository
public interface HoldingRepository extends JpaRepository<Holding, Long>{

}

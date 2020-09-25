package com.wow.server.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wow.server.data.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}

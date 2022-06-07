package com.inventory.demo.repository;

import com.inventory.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByBatchIdAndIsActive(String batchId, Boolean isActive);
}

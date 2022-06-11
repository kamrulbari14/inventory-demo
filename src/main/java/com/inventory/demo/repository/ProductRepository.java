package com.inventory.demo.repository;

import com.inventory.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductNameAndIsActive(String name, Integer isActive);

    Optional<Product> findByIdAndIsActive(Long id, Integer isActive);
}

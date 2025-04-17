package com.example.myProject.repository;

import com.example.myProject.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllBySellerId(Long id);

    List<Product> findAllByDeletedFalse();
}

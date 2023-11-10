package com.example.iamport.repository;

import com.example.iamport.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

      Product findByProductName(String productName);
}

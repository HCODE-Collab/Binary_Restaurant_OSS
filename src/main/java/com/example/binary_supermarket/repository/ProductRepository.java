package com.example.binary_supermarket.repository;

import com.example.binary_supermarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

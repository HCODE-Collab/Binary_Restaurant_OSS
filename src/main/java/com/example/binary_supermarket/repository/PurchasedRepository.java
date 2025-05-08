package com.example.binary_supermarket.repository;

import com.example.binary_supermarket.entity.Purchased;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchasedRepository extends JpaRepository<Purchased, Long> {
    List<Purchased> findByCustomerId(Long customerId);



}

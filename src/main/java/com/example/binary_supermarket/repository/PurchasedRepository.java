package com.example.binary_supermarket.repository;

import com.example.binary_supermarket.entity.Purchased;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedRepository extends JpaRepository<Purchased, Long> {


}

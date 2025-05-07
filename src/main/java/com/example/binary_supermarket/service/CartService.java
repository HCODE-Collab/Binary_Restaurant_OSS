package com.example.binary_supermarket.service;

import com.example.binary_supermarket.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    public void checkout(Long customerId) {
        cartItemRepository.deleteByCustomerId(customerId);
        // save purchases, etc.
    }
}

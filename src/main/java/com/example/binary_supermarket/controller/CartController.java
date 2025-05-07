package com.example.binary_supermarket.controller;

import com.example.binary_supermarket.dto.AddToCart;
import com.example.binary_supermarket.entity.CartItem;
import com.example.binary_supermarket.repository.CartItemRepository;
import com.example.binary_supermarket.repository.CustomerRepository;
import com.example.binary_supermarket.repository.ProductRepository;
import com.example.binary_supermarket.repository.PurchasedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final PurchasedRepository purchasedRepository;
    private final CustomerRepository customerRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody AddToCart request) {
        CartItem cartItem = CartItem.builder()
                .customerId(request.getCustomerId())
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .build();
        return ResponseEntity.ok(cartItemRepository.save(cartItem));
    }

}

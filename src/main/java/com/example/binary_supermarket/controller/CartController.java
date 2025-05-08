package com.example.binary_supermarket.controller;

import com.example.binary_supermarket.dto.AddToCart;
import com.example.binary_supermarket.entity.CartItem;
import com.example.binary_supermarket.entity.Customer;
import com.example.binary_supermarket.entity.Product;
import com.example.binary_supermarket.entity.Purchased;
import com.example.binary_supermarket.repository.CartItemRepository;
import com.example.binary_supermarket.repository.CustomerRepository;
import com.example.binary_supermarket.repository.ProductRepository;
import com.example.binary_supermarket.repository.PurchasedRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCart(@PathVariable Long customerId) {
        List<CartItem> cart = cartItemRepository.findByCustomerId(customerId);
        return ResponseEntity.ok(cart);
    }
    @Transactional
    @PostMapping("/checkout/{customerId}")
    public ResponseEntity<?> checkout(@PathVariable Long customerId) {
        List<CartItem> cartItems = cartItemRepository.findByCustomerId(customerId);
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        LocalDate date = LocalDate.now();

        List<Purchased> purchases = cartItems.stream().map(item -> {
            Product product = productRepository.findById(item.getProductId()).orElseThrow();
            return Purchased.builder()
                    .customerId(customerId)
                    .CustomerName(customer.getName())
                    .productId(String.valueOf(product.getId()))
                    .productName(product.getName())
                    .quantity(item.getQuantity())
                    .unitPrice(product.getPrice())
                    .totalPrice(product.getPrice() * item.getQuantity())
                    .date(date.toString())
                    .build();
        }).collect(Collectors.toList());

        purchasedRepository.saveAll(purchases);
        cartItemRepository.deleteByCustomerId(customerId);

        return ResponseEntity.ok("Checkout complete!");

    }

}

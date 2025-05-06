package com.example.binary_supermarket.controller;

import com.example.binary_supermarket.dto.LoginRequest;
import com.example.binary_supermarket.dto.RegisterRequest;
import com.example.binary_supermarket.entity.Customer;
import com.example.binary_supermarket.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered!");
        }

        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        return ResponseEntity.ok(customerRepository.save(customer));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return customerRepository.findByEmail(request.getEmail())
                .filter(c -> passwordEncoder.matches(request.getPassword(), c.getPassword()))
                .map(c -> ResponseEntity.ok("Login successful!"))
                .orElse(ResponseEntity.status(401).body("Invalid email or password."));
    }
}

package com.example.binary_supermarket.dto;

import lombok.Data;

@Data
public class AddToCart {
    private Long productId;
    private Long customerId;
    private int quantity;

}

package com.example.binary_supermarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseReportDTO {

    private int no;
    private String customerName;
    private String date;
    private String productId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double totalPrice;



}

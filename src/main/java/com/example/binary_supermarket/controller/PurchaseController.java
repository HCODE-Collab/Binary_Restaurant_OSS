package com.example.binary_supermarket.controller;

import com.example.binary_supermarket.dto.PurchaseReportDTO;
import com.example.binary_supermarket.entity.Purchased;
import com.example.binary_supermarket.repository.PurchasedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchasedRepository purchasedRepository;

    @GetMapping
    public ResponseEntity<?> all() {
        List<Purchased> purchases = purchasedRepository.findAll();
        List<PurchaseReportDTO> report = new ArrayList<>();
        int counter = 1;

        for (Purchased p : purchases) {
            report.add(new PurchaseReportDTO(
                    counter++,
                    p.getCustomerName(),
                    p.getDate(),
                    p.getProductId(),
                    p.getProductName(),
                    p.getQuantity(),
                    p.getUnitPrice(),
                    p.getTotalPrice()
            ));
        }

        return ResponseEntity.ok(report);
    }
}

package com.example.binary_supermarket.controller;

import com.example.binary_supermarket.dto.PurchaseReportDTO;
import com.example.binary_supermarket.entity.Purchased;
import com.example.binary_supermarket.repository.PurchasedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/purchases") // consistent with frontend: /purchases/{customerId}
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchasedRepository purchasedRepository;

    @GetMapping("/{customerId}")
    public ResponseEntity<List<PurchaseReportDTO>> getCustomerPurchases(@PathVariable Long customerId) {
        List<Purchased> purchases = purchasedRepository.findByCustomerId(customerId);
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

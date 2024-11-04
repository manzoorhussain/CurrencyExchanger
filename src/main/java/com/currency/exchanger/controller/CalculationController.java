package com.currency.exchanger.controller;


import com.currency.exchanger.model.Bill;
import com.currency.exchanger.service.CurrencyService;
import com.currency.exchanger.service.DiscountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class CalculationController {



    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private DiscountService discountService;

    @PostMapping("/calculate")
    public ResponseEntity<Double> calculate(@RequestBody Bill bill, @RequestParam String targetCurrency) {
        double discountedAmount = discountService.applyDiscounts(bill);
        double exchangeRate = currencyService.getExchangeRate(bill.getCurrency(), targetCurrency);
        double finalAmount = discountedAmount * exchangeRate;

        return ResponseEntity.ok(finalAmount);
    }
}

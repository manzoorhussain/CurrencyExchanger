package com.currency.exchanger.service;

import com.currency.exchanger.model.Bill;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class DiscountService {

    private static boolean isCustomerOverTwoYears(Date customerSince) {
        // Convert Date to LocalDate
        LocalDate customerDate = customerSince.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        // Calculate the date two years after the customer date
        LocalDate twoYearsLater = customerDate.plusYears(2);

        // Check if the current date is after the date two years later
        return LocalDate.now().isAfter(twoYearsLater);
    }

    public double applyDiscounts(Bill bill) {
        double discount = 0;

        // Determine the percentage-based discount using switch expression
        discount += switch (bill.getUser().getUserType()) {
            case "EMPLOYEE" -> 0.30;
            case "AFFILIATE" -> 0.10;
            default -> isCustomerOverTwoYears(bill.getUser().getCustomerSince()) ? 0.05 : 0.0;
        };

        // Flat $5 discount for every $100
        double flatDiscount = Math.floor(bill.getTotalAmount() / 100) * 5;

        return bill.getTotalAmount() * (1 - discount) - flatDiscount;
    }
}

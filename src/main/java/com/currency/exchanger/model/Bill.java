package com.currency.exchanger.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Bill {

    private List<Item> items;
    private double totalAmount;
    private String currency;
    private User user;
}

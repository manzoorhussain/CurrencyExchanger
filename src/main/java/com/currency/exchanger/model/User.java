package com.currency.exchanger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;



@Getter
@Setter

public class User {

    private String userType; // EMPLOYEE, AFFILIATE, CUSTOMER
    private Date customerSince;
}

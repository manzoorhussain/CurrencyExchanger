package com.currency.exchanger.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class ExchangeRateResponse {


    private String result;
    private String baseCode;
    private Map<String, Double> rates;
}

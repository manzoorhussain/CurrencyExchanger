package com.currency.exchanger.service;

import com.currency.exchanger.response.ExchangeRateClient;
import com.currency.exchanger.response.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {

    @Autowired
    private ExchangeRateClient exchangeRateClient;

    @Value("${api.key}")
    private final String apiKey=""; // replace with actual API key



    public double getExchangeRate(String base, String target) {

        ExchangeRateResponse response = exchangeRateClient.getLatestExchangeRates(base, apiKey);
        return response.getRates().get(target);
    }


}

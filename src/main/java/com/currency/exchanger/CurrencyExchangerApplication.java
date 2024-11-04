package com.currency.exchanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyExchangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangerApplication.class, args);
	}

}

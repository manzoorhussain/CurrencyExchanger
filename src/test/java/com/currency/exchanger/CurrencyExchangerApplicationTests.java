package com.currency.exchanger;

import com.currency.exchanger.controller.CalculationController;
import com.currency.exchanger.model.Bill;
import com.currency.exchanger.model.Item;
import com.currency.exchanger.model.User;
import com.currency.exchanger.service.CurrencyService;
import com.currency.exchanger.service.DiscountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CurrencyExchangerApplicationTests {

	@Mock
	private CurrencyService currencyService;

	@Mock
	private DiscountService discountService;

	@InjectMocks
	private CalculationController calculationController;


	public CurrencyExchangerApplicationTests() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void setupBill() throws Exception {

		List<Item> items = new ArrayList<>();

		Item apple = new Item();
		apple.setName("Apple");
		apple.setCategory("Fruit");
		apple.setPrice(1.2);
		items.add(apple);

		Item milk = new Item();
		milk.setName("Milk");
		milk.setCategory("Dairy");
		milk.setPrice(0.99);
		items.add(milk);

		// Set up the Bill object
		Bill bill = new Bill();
		bill.setItems(items);
		bill.setTotalAmount(2.19);
		bill.setCurrency("USD");

		// Set up the user
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date customerSince = sdf.parse("2019-08-24T14:15:22Z");
		User user = new User();
		user.setUserType("EMPLOYEE");
		user.setCustomerSince(customerSince);
		bill.setUser(user);

		// Mocking the behavior of discountService and currencyService
		double discountedAmount = 70.0; // Expected amount after discount
		when(discountService.applyDiscounts(any(Bill.class))).thenReturn(discountedAmount);
		when(currencyService.getExchangeRate("USD", "EUR")).thenReturn(0.85); // Mock exchange rate

		// Act
		ResponseEntity<Double> response = calculationController.calculate(bill, "EUR");

		// Assert
		double expectedFinalAmount = discountedAmount * 0.85; // Final amount in EUR
		System.out.println(response.getBody());
		assertEquals(expectedFinalAmount, response.getBody());
	}


}

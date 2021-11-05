package com.b.simple.design.business.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;
import com.b.simple.design.model.customer.ProductImpl;
import com.b.simple.design.model.customer.ProductType;

public class RefactoredCustomerBOTest {

	private CustomerBO customerBO = new CustomerBOImpl();

	@Test
	public void testCustomerProductSum_TwoProductsSameCurrencies()
			throws DifferentCurrenciesException {
		final Currency[] currencies = {Currency.EURO, Currency.EURO};
		final BigDecimal[] amounts = { new BigDecimal("5.0"), new BigDecimal("6.0") };
		List<Product> products = createProductList(currencies, amounts);

		Amount actual = customerBO.getCustomerProductsSum(products);

		final Currency expectedCurrency = Currency.EURO;
		BigDecimal expectedAmount = Arrays.stream(amounts).reduce(BigDecimal.ZERO,BigDecimal::add);
		assertEquals(expectedCurrency, actual.getCurrency());
		assertEquals(expectedAmount, actual.getValue());
	}

	@Test
	public void testCustomerProductSum_differentCurrencies_throwsException() throws DifferentCurrenciesException {

		Currency[] currencies  = {Currency.INDIAN_RUPEE, Currency.EURO};
		BigDecimal[] amounts = {new BigDecimal("5.0"), new BigDecimal("6.0")};

		List<Product> products = createProductList(currencies, amounts);

		//JUnit 5
		assertThrows(DifferentCurrenciesException.class, ()->{
			customerBO.getCustomerProductsSum(products);
		});

	}

	@Test
	public void testCustomerProductSum2() {

		List<Product> products = new ArrayList<Product>();

		Amount temp = null;

		try {
			temp = customerBO.getCustomerProductsSum(products);
		} catch (DifferentCurrenciesException e) {
		}
		assertEquals(Currency.EURO, temp.getCurrency());
		assertEquals(BigDecimal.ZERO, temp.getValue());
	}

	private List<Product> createProductList(Currency[] currencies, BigDecimal[] amount) {
		List<Product> products = new ArrayList<>();

		for (int i = 0; i < currencies.length; i++) {
			products.add(
					new ProductImpl(i, "Product " +  i, ProductType.BANK_GUARANTEE,
							new AmountImpl(amount[i], currencies[i])));
		}

		return products;
	}

}
package com.jpmorgan.supersimplestocks;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jpmorgan.supersimplestocks.domain.model.Exchange;

/**
 * Testing {@link Exchange#calculateIndex()} 
 * 
 * @author Csaba Soti <csaba.soti.mail@gmail.com>
 */
public class ExchangeTest extends CommonTest {

	/**
	 * Initialise exchange and stocks
	 */
	@BeforeClass
	public static void init() {
		prepareExchange();
	}

	/**
	 * Testing stock calculation with GIN (preferred) stock
	 */
	@Test
	public void testPreferredStock() {
		assertEquals(new BigDecimal("1.00"), exchange.calculateIndex());

		trade(new BigDecimal("100.00"), TEA_STOCK_SYMBOL, 1);

		//  StockPrices: TEA=100
		assertEquals(new BigDecimal("100.00"), exchange.calculateIndex());

		trade(new BigDecimal("100.00"), POP_STOCK_SYMBOL, 1);

		//  StockPrices: TEA=100, POP=100
		assertEquals(new BigDecimal("100.00"), exchange.calculateIndex());

		trade(new BigDecimal("100.00"), GIN_STOCK_SYMBOL, 5);

		//  StockPrices: TEA=100, POP=100, GIN=100
		assertEquals(new BigDecimal("100.00"), exchange.calculateIndex());

		trade(new BigDecimal("102.00"), TEA_STOCK_SYMBOL, 1);

		//  StockPrices: TEA=101, POP=100, GIN=100
		assertEquals(new BigDecimal("100.33"), exchange.calculateIndex());

		trade(new BigDecimal("110.00"), POP_STOCK_SYMBOL, 1);

		//  StockPrices: TEA=101, POP=105, GIN=100
		assertEquals(new BigDecimal("101.98"), exchange.calculateIndex());


		trade(new BigDecimal("115.00"), GIN_STOCK_SYMBOL, 5);

		//  StockPrices: TEA=101, POP=105, GIN=107.50
		assertEquals(new BigDecimal("104.47"), exchange.calculateIndex());

		trade(new BigDecimal("120.00"), ALE_STOCK_SYMBOL, 1);

		//  StockPrices: TEA=101, POP=105, GIN=107.50, ALE=120
		assertEquals(new BigDecimal("108.15"), exchange.calculateIndex());

		trade(new BigDecimal("125.00"), JOE_STOCK_SYMBOL, 1);

		//  StockPrices: TEA=101, POP=105, GIN=107.50, ALE=120, JOE=125
		assertEquals(new BigDecimal("111.33"), exchange.calculateIndex());
		
	}

	
}

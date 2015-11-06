package com.jpmorgan.supersimplestocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jpmorgan.supersimplestocks.domain.model.Stock;
import com.jpmorgan.supersimplestocks.domain.model.TradingHistory;

public class StockTest extends CommonTest {

	/** Price to initialise stock prices */
	private static final BigDecimal FIRST_PRICE = new BigDecimal("100.00");

	/** TEA stock object */
	private static Stock TEAStock;

	/** POP stock object */
	private static Stock POPStock;

	/** GIN stock object */
	private static Stock GINStock;

	/**
	 * Initialise exchange and stocks.
	 * Recording 3 trades.
	 */
	@BeforeClass
	public static void init() {
		prepareExchange();
		TEAStock = exchange.getStockBySymbol(TEA_STOCK_SYMBOL);
		POPStock = exchange.getStockBySymbol(POP_STOCK_SYMBOL);
		GINStock = exchange.getStockBySymbol(GIN_STOCK_SYMBOL);
		trade(FIRST_PRICE, TEA_STOCK_SYMBOL, 1);
		trade(FIRST_PRICE, POP_STOCK_SYMBOL, 1);
		trade(FIRST_PRICE, GIN_STOCK_SYMBOL, 1);
	}

	/**
	 * Testing stock calculation with TEA stock
	 */
	@Test
	public void testTEA() {

		assertNotNull(TEAStock);

		// Testing first step: only one trade exists with TEA, price = FIRST_PRICE
		BigDecimal actualPrice = FIRST_PRICE;
		assertEquals(actualPrice, TEAStock.getTickerPrice());
		assertEquals(actualPrice, TEAStock.calculateStockPrice());
		// TEA is COMMON stock, and lastDividend = 0, so DividendYield = 0
		assertEquals(new BigDecimal("0.00"), TEAStock.calculateDividendYield());
		// TEA is COMMON stock, and lastDividend = 0, so P/E Ratio is null
		assertNull(TEAStock.calculatePERatio());

		// Testing next step: 2 trades with TEA, new price = 102
		actualPrice = new BigDecimal("102.00");
		trade(actualPrice, TEA_STOCK_SYMBOL, 1);
		assertEquals(actualPrice, TEAStock.getTickerPrice());
		// Stock price = 100 + 102 / 2 = 101
		assertEquals(new BigDecimal("101.00"), TEAStock.calculateStockPrice());
		// TEA is COMMON stock, and lastDividend = 0, so DividendYield = 0
		assertEquals(new BigDecimal("0.00"), TEAStock.calculateDividendYield());
		// TEA is COMMON stock, and lastDividend = 0, so P/E Ratio is null
		assertNull(TEAStock.calculatePERatio());

		// Testing next step: 3 trades with TEA, new price = 104
		actualPrice = new BigDecimal("104.00");
		trade(actualPrice, TEA_STOCK_SYMBOL, 1);
		assertEquals(actualPrice, TEAStock.getTickerPrice());
		// Stock price = 100 + 102 + 104 / 3 = 102
		assertEquals(new BigDecimal("102.00"), TEAStock.calculateStockPrice());

	}

	/**
	 * Testing stock calculation with POP stock
	 */
	@Test
	public void testPOP() {

		assertNotNull(POPStock);

		// Testing first step: only one trade exists with POP, price = FIRST_PRICE
		// Test number of trades of POP
		assertEquals(1, TradingHistory.getInstance().getLastFifteenMinutesTrades(POP_STOCK_SYMBOL).size());
		BigDecimal actualPrice = FIRST_PRICE;
		assertEquals(actualPrice, POPStock.getTickerPrice());
		assertEquals(actualPrice, POPStock.calculateStockPrice());
		// POP is COMMON stock, and lastDividend = 8, so DividendYield = 8 / actualPrice = 8 / 100 = 0.08
		assertEquals(new BigDecimal("0.08"), POPStock.calculateDividendYield());
		// POP is COMMON stock, and lastDividend = 8, so P/E Ratio = actualPrice / 8 = 100 / 8 = 12.5
		assertEquals(new BigDecimal("12.50"), POPStock.calculatePERatio());

		
		// Testing next step: 2 trades with POP (quantity:2), new price = 110
		actualPrice = new BigDecimal("110.00");
		trade(actualPrice, POP_STOCK_SYMBOL, 2);
		// Test number of trades of POP
		assertEquals(2, TradingHistory.getInstance().getLastFifteenMinutesTrades(POP_STOCK_SYMBOL).size());

		assertEquals(actualPrice, POPStock.getTickerPrice());
		// Stock price = 100 + (2*110) / 1 + 2 = 320 / 3 = 106.67
		assertEquals(new BigDecimal("106.67"), POPStock.calculateStockPrice());
		// POP is COMMON stock, and lastDividend = 8, so DividendYield = 8 / actualPrice = 8 / 110 = 0.07
		assertEquals(new BigDecimal("0.07"), POPStock.calculateDividendYield());
		// POP is COMMON stock, and lastDividend = 8, so P/E Ratio = actualPrice / 8 = 110 / 8 = 13.75
		assertEquals(new BigDecimal("13.75"), POPStock.calculatePERatio());

		
		// Testing next step: 3 trades with POP, new price = 104
		actualPrice = new BigDecimal("104.00");
		trade(actualPrice, POP_STOCK_SYMBOL, 1);
		// Test number of trades of POP
		assertEquals(3, TradingHistory.getInstance().getLastFifteenMinutesTrades(POP_STOCK_SYMBOL).size());

		assertEquals(actualPrice, POPStock.getTickerPrice());
		// Stock price = 100 + (2*110) + 104 / 1 + 2 + 1 = 424 / 4 = 106
		assertEquals(new BigDecimal("106.00"), POPStock.calculateStockPrice());
		// POP is COMMON stock, and lastDividend = 8, so DividendYield = 8 / actualPrice = 8 / 104 = 0.08
		assertEquals(new BigDecimal("0.08"), POPStock.calculateDividendYield());
		// POP is COMMON stock, and lastDividend = 8, so P/E Ratio = actualPrice / 8 = 104 / 8 = 13
		assertEquals(new BigDecimal("13.00"), POPStock.calculatePERatio());

	}

	/**
	 * Testing stock calculation with GIN (preferred) stock
	 */
	@Test
	public void testPreferredStock() {

		assertNotNull(GINStock);

		// Testing first step: only one trade exists with GIN, price = FIRST_PRICE
		// Test number of trades of GIN
		assertEquals(1, TradingHistory.getInstance().getLastFifteenMinutesTrades(GIN_STOCK_SYMBOL).size());

		BigDecimal actualPrice = FIRST_PRICE;
		assertEquals(actualPrice, GINStock.getTickerPrice());
		assertEquals(actualPrice, GINStock.calculateStockPrice());
		// GIN is PREFERRED stock, and fixedDividend=0.02, parValue=100 so
		// DividendYield = 0.02*100 / actualPrice = 2 / 100 = 0.02
		assertEquals(new BigDecimal("0.02"), GINStock.calculateDividendYield());
		// GIN is PREFERRED stock, and lastDividend = 8, so P/E Ratio = actualPrice / 8 = 100 / 8 = 12.5
		assertEquals(new BigDecimal("12.50"), GINStock.calculatePERatio());

		
		// Testing next step: 2 trades with GIN (quantity:2), new price = 115
		actualPrice = new BigDecimal("115.00");
		trade(actualPrice, GIN_STOCK_SYMBOL, 2);

		// Test number of trades of GIN
		assertEquals(2, TradingHistory.getInstance().getLastFifteenMinutesTrades(GIN_STOCK_SYMBOL).size());

		assertEquals(actualPrice, GINStock.getTickerPrice());
		// Stock price = 100 + (2*115) / 1 + 2 = 330 / 3 = 110.00
		assertEquals(new BigDecimal("110.00"), GINStock.calculateStockPrice());
		// GIN is PREFERRED stock, and fixedDividend=0.02, parValue=100, so
		// DividendYield = 0.02*100 / actualPrice = 2 / 115 = 0.017
		assertEquals(new BigDecimal("0.02"), GINStock.calculateDividendYield());
		// GIN is PREFERRED stock, and lastDividend = 8, so P/E Ratio = actualPrice / 8 = 115 / 8 = 14.375
		assertEquals(new BigDecimal("14.38"), GINStock.calculatePERatio());


		// Testing next step: 3 trades with GIN, new price = 104
		actualPrice = new BigDecimal("104.00");
		trade(actualPrice, GIN_STOCK_SYMBOL, 1);
		assertEquals(actualPrice, GINStock.getTickerPrice());
		// Stock price = 100 + (2*115) + 104 / 1 + 2 + 1 = 434 / 4 = 108.5
		assertEquals(new BigDecimal("108.50"), GINStock.calculateStockPrice());
		// GIN is PREFERRED stock, and lastDividend = 8, so DividendYield = 8 / actualPrice = 8 / 104 = 0.076
		assertEquals(new BigDecimal("0.02"), GINStock.calculateDividendYield());
		// GIN is PREFERRED stock, and lastDividend = 8, so P/E Ratio = actualPrice / 8 = 104 / 8 = 13
		assertEquals(new BigDecimal("13.00"), GINStock.calculatePERatio());

	}
}

package com.jpmorgan.supersimplestocks;

import java.math.BigDecimal;

import org.junit.AfterClass;

import com.jpmorgan.supersimplestocks.domain.model.Exchange;
import com.jpmorgan.supersimplestocks.domain.model.Stock;
import com.jpmorgan.supersimplestocks.domain.model.StockInfo;
import com.jpmorgan.supersimplestocks.domain.model.StockType;
import com.jpmorgan.supersimplestocks.domain.model.Trade;
import com.jpmorgan.supersimplestocks.domain.model.TradingHistory;

/**
 * Common constants and methods for JUnit tests. 
 * 
 * @author Csaba Soti <csaba.soti.mail@gmail.com> <csaba.soti.mail@gmail.com>
 */
public class CommonTest {

	/** TEA stock symbol */
	protected static final String TEA_STOCK_SYMBOL = "TEA";

	/** POP stock symbol */
	protected static final String POP_STOCK_SYMBOL = "POP";

	/** ALE stock symbol */
	protected static final String ALE_STOCK_SYMBOL = "ALE";

	/** GIN stock symbol */
	protected static final String GIN_STOCK_SYMBOL = "GIN";

	/** JOE stock symbol */
	protected static final String JOE_STOCK_SYMBOL = "JOE";

	/** Current exchange, with defined list of stocks. */
	protected static Exchange exchange;

	/**
	 * Creating exchange with stocks.
	 */
	protected static void prepareExchange() {
		exchange = Exchange.getInstance();
		exchange.addStock(new Stock(TEA_STOCK_SYMBOL, StockType.COMMON, BigDecimal.ZERO, new BigDecimal("100.0")));
		exchange.addStock(
				new Stock(POP_STOCK_SYMBOL, StockType.COMMON, new BigDecimal("8.0"), new BigDecimal("100.0")));
		exchange.addStock(
				new Stock(ALE_STOCK_SYMBOL, StockType.COMMON, new BigDecimal("23.0"), new BigDecimal("60.0")));
		exchange.addStock(new Stock(GIN_STOCK_SYMBOL, StockType.PREFERRED, new BigDecimal("8.0"),
				new BigDecimal("100.0"), new BigDecimal("0.02")));
		exchange.addStock(
				new Stock(JOE_STOCK_SYMBOL, StockType.COMMON, new BigDecimal("13.0"), new BigDecimal("250.0")));
	}

	/**
	 * Constructs and records a trade.
	 * 
	 * @param price The ticker price
	 * @param symbol The stock symbol
	 * @param quantity The quantity
	 */
	protected static void trade(BigDecimal price, String symbol, int quantity) {
		final Trade trade = new Trade();
		trade.setSymbol(symbol);
		trade.setTickerPrice(price);
		trade.setQuantity(quantity);
		TradingHistory.getInstance().record(trade);
	}

	/**
	 * Terminates the Simulator.
	 */
	@AfterClass
	public static void tearDown() {
		TradingHistory.getInstance().reset();
		StockInfo.getInstance().reset();
	}

}

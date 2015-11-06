package com.jpmorgan.supersimplestocks.domain.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Stock info contains the up-to-date values of stock trading.
 * 
 * @author Csaba Soti
 */
public class StockInfo {

	/** The singleton instance */
	private static final StockInfo instance = new StockInfo();

	/** The internal cache to store latest prices */
	private Map<String, BigDecimal> cache = new HashMap<String, BigDecimal>();

	/**
	 * Retrieves the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	public static StockInfo getInstance() {
		return instance;
	}

	/**
	 * Updates the stock price cache
	 * 
	 * @param trade
	 */
	public void update(final Trade trade) {
		cache.put(trade.getSymbol(), trade.getTickerPrice());
	}

	/**
	 * Retrieves last price from the cache.
	 * 
	 * @param symbol
	 *            symbol key to find stock
	 * @return last price
	 */
	public BigDecimal getTickerPrice(final String symbol) {
		return cache.get(symbol);
	}

}

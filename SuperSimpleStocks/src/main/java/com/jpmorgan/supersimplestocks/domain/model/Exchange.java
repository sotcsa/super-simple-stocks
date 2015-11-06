package com.jpmorgan.supersimplestocks.domain.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The exchange domain model to store stocks and to calculate index.
 * 
 * @author Csaba Soti
 */
public class Exchange {

	/** The singleton instance. */
	private static final Exchange instance = new Exchange("GBCE");

	/** The inner map to stock stocks */
	private final Map<String, Stock> stockMap = new LinkedHashMap<String, Stock>();

	/** The exchange name */
	private String name;

	/**
	 * The hidden class constructor.
	 * 
	 * @param name
	 *            exchange name
	 * 
	 */
	private Exchange(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	public static Exchange getInstance() {
		return instance;
	}

	/**
	 * Puts an stock to the inner map.
	 * 
	 * @param stock
	 *            adding symbol as key and object as value
	 */
	public void addStock(final Stock stock) {
		synchronized (this) {
			stockMap.put(stock.getSymbol(), stock);
		}

	}

	/**
	 * Calculates the index of all contained stocks.
	 * 
	 * @return the GBCE All Share Index
	 */
	public BigDecimal calculateIndex() {
		BigDecimal product = BigDecimal.ONE;
		for (Stock stock : stockMap.values()) {
			BigDecimal stockPrice = stock.calculateStockPrice();
			if (stockPrice != null) {
				product = product.multiply(stockPrice);
			}
		}
		return new BigDecimal(Math.pow(product.doubleValue(), 1.0 / getSize()));
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves size of containing stock list
	 * @return
	 */
	public int getSize() {
		return stockMap.size();
	}

	/**
	 * @return the stockMap
	 */
	public Map<String, Stock> getStockMap() {
		return stockMap;
	}

	/**
	 * Retrieves containing stocks;
	 * 
	 * @return stock list
	 */
	public Collection<Stock> getStocks() {
		return stockMap.values();
	}

	public Stock getStockByIndex(int stockIndex) {
		Iterator<Entry<String, Stock>> iterator = stockMap.entrySet().iterator();
		int n = 0;
		while (iterator.hasNext()) {
			Entry<String, Stock> entry = iterator.next();
			if (n == stockIndex) {
				return entry.getValue();
			}
			n++;
		}
		return null;
	}

}

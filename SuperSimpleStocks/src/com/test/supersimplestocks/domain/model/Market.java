package com.test.supersimplestocks.domain.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The market domain model to store stocks and to calculate index.
 * 
 * @author Csaba Soti
 */
public class Market {

	/** The singleton instance. */
	private static final Market instance = new Market("GBCE");

	/** The inner map to stock stocks */
	private final Map<String, Stock> stockMap = new LinkedHashMap<String, Stock>();

	/** The market name */
	private String name;

	/**
	 * The hidden class constructor.
	 * 
	 * @param name
	 *            market name
	 * 
	 */
	private Market(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	public static Market getInstance() {
		return instance;
	}

	/**
	 * Puts an stock to the inner map.
	 * 
	 * @param stockSymbol
	 *            using as key
	 * @param stock
	 *            using as value
	 */
	public void put(final String stockSymbol, final Stock stock) {
		synchronized (this) {
			stockMap.put(stockSymbol, stock);
		}

	}

	/**
	 * Calculates the index of all contained stocks.
	 * 
	 * @return the GBCE All Share Index
	 */
	public double calculateIndex() {
		double product = 1.0;
		for (Stock stock : stockMap.values()) {
			Double stockPrice = stock.calculateStockPrice();
			if (stockPrice != null) {
				product = product * stockPrice;
			}
		}
		return Math.pow(product, 1.0 / getLength());
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public int getLength() {
		return stockMap.size();
	}

	/**
	 * @return the stockMap
	 */
	public Map<String, Stock> getStockMap() {
		return stockMap;
	}

	public Collection<Stock> getStocks() {
		return stockMap.values();
	}

	// public Stock getStockBySymbol(final String symbol) {
	// return stockMap.get(symbol);
	// }

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

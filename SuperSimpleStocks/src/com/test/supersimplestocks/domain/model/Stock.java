package com.test.supersimplestocks.domain.model;

import java.util.List;

/**
 * The stock domain model. It can calculate stock related values from trading
 * system.
 * 
 * @author Csaba Soti
 */
public class Stock {

	/** The stock symbol */
	private final String symbol;

	/** The stock type */
	private final StockType type;

	/** The last dividend */
	private Double lastDividend;

	/** The par value */
	private Double parValue;

	/** The fixed dividend */
	private Double fixedDividend;

	/** The place where price of stocks is stored */
	private StockInfo stockInfo = StockInfo.getInstance();

	/**
	 * Class constructor.
	 * 
	 * @param symbol
	 * @param type
	 * @param lastDividend
	 * @param parValue
	 */
	public Stock(final String symbol, StockType type, final Double lastDividend, final Double parValue) {
		this.symbol = symbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.parValue = parValue;
	}

	/**
	 * Class constructor.
	 * 
	 * @param symbol
	 * @param type
	 * @param lastDividend
	 * @param parValue
	 * @param fixedDividend
	 */
	public Stock(final String symbol, StockType type, final Double lastDividend, final Double parValue,
			final Double fixedDividend) {
		this(symbol, type, lastDividend, parValue);
		this.fixedDividend = fixedDividend;
	}

	/**
	 * Calculates he dividend yield.
	 * 
	 * @return calculated stock price
	 */
	public Double calculateDividendYield() {
		Double result;
		switch (type) {
			case PREFERRED:
				result = (fixedDividend * parValue) / getTickerPrice();
				break;
			default:
				result = lastDividend / getTickerPrice();
				break;
		}
		return result;
	}

	/**
	 * Calculates the P/E Ration.
	 * 
	 * @return calculated stock price
	 */
	public Double calculatePERatio() {
		Double dividendYield = calculateDividendYield();
		if (dividendYield != 0.0) {
			return getTickerPrice() / calculateDividendYield();
		}
		return null;
	}

	/**
	 * Calculates the Stock Price based on trades recorded in past 15 minutes.
	 * 
	 * @return calculated stock price
	 */
	public Double calculateStockPrice() {
		final List<Trade> trades = TradingHistory.getInstance().getLastFifteenMinutesTradesWithoutLock(symbol);
		double sumOfProduct = 0.0;
		double quantities = 0.0;
		for (Trade trade : trades) {
			sumOfProduct += trade.getTickerPrice() * trade.getQuantity();
			quantities += trade.getQuantity();
		}
		return sumOfProduct / quantities;
	}

	private Double getTickerPrice() {
		return stockInfo.getTickerPrice(symbol);
	}

	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param lastDividend
	 *            the lastDividend to set
	 */
	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}

	/**
	 * @param parValue
	 *            the parValue to set
	 */
	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}

	/**
	 * @param fixedDividend
	 *            the fixedDividend to set
	 */
	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

}

package com.jpmorgan.supersimplestocks.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * The stock domain model. It can calculate stock related values from trading
 * system.
 * 
 * @author Csaba Soti <csaba.soti.mail@gmail.com>
 */
public class Stock {

	/** The stock symbol */
	private final String symbol;

	/** The stock type */
	private final StockType type;

	/** The last dividend */
	private BigDecimal lastDividend;

	/** The par value */
	private BigDecimal parValue;

	/** The fixed dividend */
	private BigDecimal fixedDividend;

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
	public Stock(final String symbol, StockType type, final BigDecimal lastDividend, final BigDecimal parValue) {
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
	public Stock(final String symbol, StockType type, final BigDecimal lastDividend, final BigDecimal parValue,
			final BigDecimal fixedDividend) {
		this(symbol, type, lastDividend, parValue);
		this.fixedDividend = fixedDividend;
	}

	/**
	 * Calculates he dividend yield.
	 * 
	 * @return calculated stock price
	 */
	public BigDecimal calculateDividendYield() {
		BigDecimal result;
		switch (type) {
		case PREFERRED:
			result = (fixedDividend.multiply(parValue)).divide(getTickerPrice(), 2, RoundingMode.HALF_EVEN);
			break;
		default:
			result = lastDividend.divide(getTickerPrice(), 2, RoundingMode.HALF_EVEN);
			break;
		}
		return result;
	}

	/**
	 * Calculates the P/E Ration.
	 * 
	 * @return calculated stock price
	 */
	public BigDecimal calculatePERatio() {
		if (lastDividend != BigDecimal.ZERO) {
			return getTickerPrice().divide(lastDividend, 2, RoundingMode.HALF_EVEN);
		}
		return null;
	}

	/**
	 * Calculates the Stock Price based on trades recorded in past 15 minutes.
	 * 
	 * @return calculated stock price
	 */
	public BigDecimal calculateStockPrice() {
		final List<Trade> trades = TradingHistory.getInstance().getLastFifteenMinutesTrades(symbol);
		BigDecimal sumOfProduct = BigDecimal.ZERO;
		int quantities = 0;
		for (Trade trade : trades) {
			sumOfProduct = sumOfProduct.add(trade.getTickerPrice().multiply(new BigDecimal(trade.getQuantity())));
			quantities += trade.getQuantity();
		}
		if (quantities == 0) {
			return null;
		} else {
			return sumOfProduct.divide(new BigDecimal(quantities), 2, RoundingMode.HALF_EVEN);
		}
	}

	/**
	 * Retrieves the last price of stock.
	 * 
	 * @return price
	 */
	public BigDecimal getTickerPrice() {
		return stockInfo.getTickerPrice(symbol);
	}

	/**
	 * Retrieves the stock symbol.
	 * 
	 * @return stock symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param lastDividend
	 *            the lastDividend to set
	 */
	public void setLastDividend(BigDecimal lastDividend) {
		this.lastDividend = lastDividend;
	}

	/**
	 * @param parValue
	 *            the parValue to set
	 */
	public void setParValue(BigDecimal parValue) {
		this.parValue = parValue;
	}

	/**
	 * @param fixedDividend
	 *            the fixedDividend to set
	 */
	public void setFixedDividend(BigDecimal fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

}

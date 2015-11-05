package com.jpmorgan.supersimplestocks.domain.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Domain model to store a trade.
 * 
 * @author Csaba Soti
 */
public class Trade {

	/** The stock symbol */
	private String symbol;

	/** Trade time */
	private final Date time = new Date();

	/** The last price */
	private BigDecimal tickerPrice;

	/** The number of stocks */
	private int quantity;

	/** The type of trade */
	private TradeType tradeType;

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 *            the symbol to set
	 */
	public void setSymbol(final String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @return the tickerPrice
	 */
	public BigDecimal getTickerPrice() {
		return tickerPrice;
	}

	/**
	 * @param tickerPrice
	 *            the tickerPrice to set
	 */
	public void setTickerPrice(BigDecimal tickerPrice) {
		this.tickerPrice = tickerPrice;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the tradeType
	 */
	public TradeType getTradeType() {
		return tradeType;
	}

	/**
	 * @param tradeType
	 *            the tradeType to set
	 */
	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

}

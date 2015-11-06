package com.jpmorgan.supersimplestocks;

import java.math.BigDecimal;
import java.util.Random;

import com.jpmorgan.supersimplestocks.domain.model.Exchange;
import com.jpmorgan.supersimplestocks.domain.model.Stock;
import com.jpmorgan.supersimplestocks.domain.model.Trade;
import com.jpmorgan.supersimplestocks.domain.model.TradeType;
import com.jpmorgan.supersimplestocks.domain.model.TradingHistory;

/**
 * Simulates the trading system.
 * 
 * @author Csaba Soti <csaba.soti.mail@gmail.com>
 */
public class TradingSimulator implements Runnable {

	/** The random generator object */
	Random randomGenerator = new Random();

	/** The trading history storage */
	TradingHistory tradingHistory = TradingHistory.getInstance();

	/** Flag to able to terminate simulator */
	private boolean running = true;

	/**
	 * Method to stop thread.
	 */
	public void terminate() {
		running = false;
	}

	/**
	 * Starting simulator thread.
	 */
	@Override
	public void run() {
		Exchange exchange = Exchange.getInstance();
		while (running) {
			if (exchange.getSize() > 0) {
				Trade trade = new Trade();

				// Find a random stock
				int stockIndex = Math.abs(randomGenerator.nextInt()) % exchange.getSize();
				Stock stock = exchange.getStockByIndex(stockIndex);
				trade.setSymbol(stock.getSymbol());

				// Fetching a random price
				double rangeMin = 70 / 100;
				double rangeMax = 200 / 100;
				double price = (rangeMin + (rangeMax - rangeMin) * randomGenerator.nextDouble()) * 100;
				trade.setTickerPrice(new BigDecimal(price));

				// Fetching a random quantity
				trade.setQuantity(randomGenerator.nextInt(100));

				// Fetching a random Buy or Sell trade type
				trade.setTradeType(randomGenerator.nextBoolean() ? TradeType.BUY : TradeType.SELL);

				tradingHistory.record(trade);
			}
			try {
				// sleeping 10 milliseconds
				Thread.sleep(10);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

}

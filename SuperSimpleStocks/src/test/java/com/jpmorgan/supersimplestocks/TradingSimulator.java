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
 * @author Csaba Soti
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

	@Override
	public void run() {
		Exchange exchange = Exchange.getInstance();
		while (running) {
			if (exchange.getLength() > 0) {
				int stockIndex = Math.abs(randomGenerator.nextInt()) % exchange.getLength();
				Stock stock = exchange.getStockByIndex(stockIndex);
				Trade trade = new Trade();
				trade.setSymbol(stock.getSymbol());
				double rangeMin = 70/100;
				double rangeMax = 200/100;
				double price = (rangeMin + (rangeMax - rangeMin) * randomGenerator.nextDouble()) * 100;
				trade.setTickerPrice(new BigDecimal(price));
				trade.setQuantity(randomGenerator.nextInt(100));
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

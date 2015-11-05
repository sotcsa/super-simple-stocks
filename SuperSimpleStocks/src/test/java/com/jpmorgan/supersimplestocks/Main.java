package com.jpmorgan.supersimplestocks;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpmorgan.supersimplestocks.domain.model.Exchange;
import com.jpmorgan.supersimplestocks.domain.model.Stock;
import com.jpmorgan.supersimplestocks.domain.model.StockType;
import com.jpmorgan.supersimplestocks.domain.model.TradingHistory;

/**
 * Main class to start.
 * 
 * @author Csaba Soti
 */
public class Main {

	/** Current exchange, with defined list of stocks. */
	private static Exchange exchange;

	/** The trade simulator trade */
	private static TradingSimulator simulator = new TradingSimulator();

	private final static Logger logger = LoggerFactory.getLogger(Main.class);

	/**
	 * The main test. It prints out the calculations of all stock values.
	 * 
	 * @param args
	 *            This program works without parameters
	 */
	@Test
	public void printOutStockValues() {
		// Displaying the generated trades
		TradingHistory.getInstance().logStatistics();

		logger.info(String.format(String.format("%54s", " ").replace(" ", "-")));
		logger.info(String.format("%1$6s|%2$15s|%3$15s|%4$15s|", "Symbol", "Dividend Yield", "P/E Ration", "Stock Price"));
		logger.info(String.format(String.format("%54s", " ").replace(" ", "-")));
		for (Stock stock : exchange.getStocks()) {
			logger.info(String.format("%1$6s|", stock.getSymbol())
				.concat(String.format("%1$15s|", NumberFormatter.format(stock.calculateDividendYield())))
				.concat(String.format("%1$15s|", NumberFormatter.format(stock.calculatePERatio())))
				.concat(String.format("%1$15s|", NumberFormatter.format(stock.calculateStockPrice()))));
		}
		logger.info(String.format("%s All Share Index: %s\n", exchange.getName(), NumberFormatter.format(exchange.calculateIndex())));
	}

	/**
	 * Initialises the TradeSimulator and exchange.
	 */
	@Before
	public void init() {
		exchange = Exchange.getInstance();
		exchange.addStock(new Stock("TEA", StockType.COMMON,    BigDecimal.ZERO,        new BigDecimal("100.0")));
		exchange.addStock(new Stock("POP", StockType.COMMON,    new BigDecimal("8.0"),  new BigDecimal("100.0")));
		exchange.addStock(new Stock("ALE", StockType.COMMON,    new BigDecimal("23.0"), new BigDecimal("60.0")));
		exchange.addStock(new Stock("GIN", StockType.PREFERRED, new BigDecimal("8.0"),  new BigDecimal("100.0"), new BigDecimal("0.02")));
		exchange.addStock(new Stock("JOE", StockType.COMMON,    new BigDecimal("13.0"), new BigDecimal("250.0")));
		new Thread(simulator).start();
		try {
			// waiting 1 second so that simulator can generate enough test data
			Thread.sleep(1 * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Terminates the Simulator.
	 */
	@After
	public void tearDown() {
		simulator.terminate();
	}
}

package com.jpmorgan.supersimplestocks.domain.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpmorgan.supersimplestocks.utils.NumberFormatter;

/**
 * Stores historical trade data in the memory.
 * 
 * @author Csaba Soti <csaba.soti.mail@gmail.com>
 */
public class TradingHistory {

	/** The logger object */
	private final static Logger logger = LoggerFactory.getLogger(TradingHistory.class);

	/** The singleton instance */
	private static final TradingHistory instance = new TradingHistory();

	/** In-memory storage of trades. */
	private List<Trade> trades = new LinkedList<Trade>();

	/** Cache for storing latest stock price */
	private StockInfo stockInfo = StockInfo.getInstance();

	/**
	 * The hidden constructor.
	 */
	private TradingHistory() {
	}

	/**
	 * Retrieves the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	public static TradingHistory getInstance() {
		return instance;
	}

	/**
	 * Records a trade object and updates the stock price cache.
	 * 
	 * @param trade
	 */
	public void record(Trade trade) {
		trades.add(trade);
		stockInfo.update(trade);
	}

	/**
	 * Retrieves trade list created in last 15 minutes. It is not trivial to
	 * iterate over a collection, which is changing meantime. Locking is not a
	 * good solution, because it may affect write latency.
	 * 
	 * @param symbol
	 *            stock symbol to filter trades
	 * @return list of trades
	 */
	public List<Trade> getLastFifteenMinutesTrades(final String symbol) {
		final List<Trade> list = new ArrayList<Trade>();
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.MINUTE, -15);
		final Date limitTime = cal.getTime();

		// Following part is thread safe, because we does not use iterator.
		for (int i = trades.size() - 1; i >= 0; i--) {
			// Starting at the end of the list, with the latest trade
			Trade trade = trades.get(i);
			if (trade.getTime().after(limitTime)) {
				if (trade.getSymbol().equals(symbol)) {
					list.add(trade);
				}
			} else {
				// If time limit reached, exit
				break;
			}
		}
		return list;
	}

	/**
	 * Making statistics about trades. Only for testing purpose.
	 */
	public void logStatistics() {
		final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		logger.info(String.format("Nunber of trades: %d", trades.size()));
		for (int i = trades.size() - 1; i >= 0; i--) {
			Trade trade = trades.get(i);
			logger.info(
					String.format("%s %2$-5s %3$-5s %4$-5s %5$6s", sdf.format(trade.getTime()), trade.getTradeType(),
							trade.getSymbol(), trade.getQuantity(), NumberFormatter.format(trade.getTickerPrice())));
		}
	}

}

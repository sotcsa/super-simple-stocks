package com.test.supersimplestocks.domain.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import com.test.supersimplestocks.domain.model.StockInfo;
import com.test.supersimplestocks.domain.model.Trade;

/**
 * Stores historical trade data in the memory.
 * 
 * @author Csaba Soti
 */
public class TradingHistory {

	/** The singleton instance */
	private static final TradingHistory instance = new TradingHistory();

	private List<Trade> trades = new LinkedList<Trade>();

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

	public void put(Trade trade) {
		trades.add(trade);
		stockInfo.update(trade);
	}

	/**
	 * Retrieves trade list created in last 15 minutes.
	 * 
	 * @param symbol
	 *            stock symbol to filter trades
	 * @return list of trades
	 */
	public List<Trade> getLastFifteenMinutesTradesWithoutLock(final String symbol) {
		final List<Trade> list = new ArrayList<Trade>();
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.MINUTE, -15);
		final Date limitTime = cal.getTime();
		for (int i = trades.size() - 1; i >= 0; i--) {
			Trade trade = trades.get(i);
			if (trade.getTime().after(limitTime)) {
				if (trade.getSymbol().equals(symbol)) {
					list.add(trade);
				}
			} else {
				break;
			}
		}
		return list;
	}

}
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Stores historical trade data in the memory.
 * 
 * @author Csaba Soti
 */
public class TradingHistory {

	/** The singleton instance */
	private static final TradingHistory instance = new TradingHistory();

	private List<Trade> trades = new ArrayList<Trade>();

	private StockInfo stockInfo = StockInfo.getInstance();

	/**
	 * The hidden constructor.
	 */
	protected TradingHistory() {
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
	 * @param symbol stock symbol to filter trades
	 * @return list of trades
	 */
	public List<Trade> getLastFifteenMinutesTrades(final String symbol) {
		final List<Trade> list = new ArrayList<Trade>();
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.MINUTE, -15);
		final Date limitTime = cal.getTime();
		List<Trade> syncList = getTrades();
		synchronized (syncList) {
			for (Trade trade: syncList) {
				if (trade.getTime().after(limitTime)) {
					if (trade.getSymbol().equals(symbol)) {
						list.add(trade);
					}
				} else {
					break;
				}
			}
		}
		return list;
	}

	private List<Trade> getTrades() {
		return Collections.synchronizedList(trades);
	}

}

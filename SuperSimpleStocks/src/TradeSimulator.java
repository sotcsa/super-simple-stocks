import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Simulates the trading system.
 * 
 * @author Csaba Soti
 */
public class TradeSimulator implements Runnable {

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
		Market market = Market.getInstance();
		while (running) {
			if (market.getLength() > 0) {
				int stockIndex = Math.abs(randomGenerator.nextInt()) % market.getLength();
				Stock stock = market.getStockByIndex(stockIndex);
				Trade trade = new Trade();
				trade.setSymbol(stock.getSymbol());
				trade.setTickerPrice(randomGenerator.nextDouble() * 100);
				trade.setQuantity(randomGenerator.nextInt(100));
				tradingHistory.put(trade);
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

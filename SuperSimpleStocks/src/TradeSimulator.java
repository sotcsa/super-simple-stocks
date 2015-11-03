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
		System.out.println("TradeSimulator running, name: " + Thread.currentThread().getName());
		while (running) {
			if (market.getLength() > 0) {
				int stockIndex = Math.abs(randomGenerator.nextInt()) % market.getLength();
				Stock stock = market.getStockByIndex(stockIndex);
				Trade trade = new Trade();
				trade.setSymbol(stock.getSymbol());
				trade.setTickerPrice(randomGenerator.nextDouble() * 100);
				tradingHistory.put(trade);
				System.out.format("%s %s %s\n", trade.getTime(), trade.getSymbol(), trade.getTickerPrice());
			}
			try {
				// sleeping 100 milliseconds
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

}

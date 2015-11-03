/**
 * Main class to start.
 * 
 * @author Csaba Soti
 */
public class Main {

	/** Current market, with defined list of stocks. */
	private static Market market;

	/** The trade simulator trade */
	private static TradeSimulator simulator = new TradeSimulator();

	/**
	 * The main method - good start point to test project. It prints out the
	 * calculations of all stock values.
	 * 
	 * @param args
	 *            This program works without parameters
	 */
	public static void main(String[] args) {
		init();
		printOutStockValues();
	}

	/**
	 * Renders stock values and index.
	 */
	private static void printOutStockValues() {
		System.out.format("Symbol\t");
		System.out.format("Dividend Yield\t\t");
		System.out.format("P/E Ration\t\t");
		System.out.format("Stock Price\n");
		for (Stock stock : market.getStocks()) {
			System.out.format("%s\t", stock.getSymbol());
			System.out.format("%s\t\t\t", stock.calculateDividendYield());
			System.out.format("%s\t\t\t", stock.calculatePERatio());
			System.out.format("%s\n", stock.calculateStockPrice());
		}
		System.out.format("%s All Share Index: %s\n", market.getName(), market.calculateIndex());
		simulator.terminate();
	}

	/**
	 * Initialises the TradeSimulator and market.
	 */
	private static void init() {
		market = Market.getInstance();
		market.put("TEA", new Stock("TEA", StockType.COMMON, 0.0, 100.0));
		market.put("POP", new Stock("POP", StockType.COMMON, 0.08, 100.0));
		market.put("ALE", new Stock("ALE", StockType.COMMON, 0.23, 60.0));
		market.put("GIN", new Stock("GIN", StockType.PREFERRED, 0.08, 100.0, 0.02));
		market.put("JOE", new Stock("JOE", StockType.COMMON, 0.13, 250.0));
		new Thread(simulator).start();
		try {
			// sleeping 1 second
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

	}
}

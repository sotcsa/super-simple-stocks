import java.text.DecimalFormat;

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
		tearDown();
	}

	/**
	 * Renders stock values and index.
	 */
	private static final DecimalFormat df = new DecimalFormat("#,###,###,##0.00");

	private static void printOutStockValues() {
		System.out.format("%1$6s|", "Symbol");
		System.out.format("%1$15s|", "Dividend Yield");
		System.out.format("%1$15s|", "P/E Ration");
		System.out.format("%1$15s|\n", "Stock Price");
		System.out.format(String.format("%54s\n", " ").replace(" ", "-"));
		for (Stock stock : market.getStocks()) {
			System.out.format("%1$6s|", stock.getSymbol());
			System.out.format("%1$15s|", format(stock.calculateDividendYield() * 100, "%"));
			System.out.format("%1$15s|", format(stock.calculatePERatio()));
			System.out.format("%1$15s|\n", format(stock.calculateStockPrice()));
		}
		System.out.format("%s All Share Index: %s\n", market.getName(), format(market.calculateIndex()));
	}

	private static String format(Double number, String suffix) {
		if (number == null) {
			return "  -  ";
		} else {
			return df.format(number) + suffix;
		}
	}

	private static String format(Double number) {
		return format(number, "");
	}

	/**
	 * Initialises the TradeSimulator and market.
	 */
	private static void init() {
		market = Market.getInstance();
		market.put("TEA", new Stock("TEA", StockType.COMMON,    0.0, 100.0));
		market.put("POP", new Stock("POP", StockType.COMMON,    8.0, 100.0));
		market.put("ALE", new Stock("ALE", StockType.COMMON,   23.0, 60.0));
		market.put("GIN", new Stock("GIN", StockType.PREFERRED, 8.0, 100.0, 0.02));
		market.put("JOE", new Stock("JOE", StockType.COMMON,   13.0, 250.0));
		new Thread(simulator).start();
		try {
			// waiting 10 seconds so that simulator can generate enough test data
			Thread.sleep(1 * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Terminates the Simulator.
	 */
	private static void tearDown() {
		simulator.terminate();
	}
}

/**
 * Main class to start.
 * 
 * @author Csaba Soti
 */
public class Main {

	/** Current market, with defined list of stocks. */
	private static Market market;

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
	 * 
	 */
	private static void printOutStockValues() {
		System.out.format("Symbol\t");
		System.out.format("Dividend Yield\t\t");
		System.out.format("P/E Ration\t\t");
		System.out.format("Stock Price\n");
		for (Stock stock : market.getStockMap().values()) {
			System.out.format("%s\t", stock.getSymbol());
			System.out.format("%s\t\t\t", stock.calculateDividendYield());
			System.out.format("%s\t\t\t", stock.calculatePERatio());
			System.out.format("%s\n", stock.calculateStockPrice());
		}
		System.out.format("%s All Share Index: %s\t", market.getName(), market.calculateIndex());
		
	}

	private static void init() {
		new Thread(new TradeSimulator()).start();
		market = new Market("GBCE");
		market.put("TEA", new Stock("TEA", 0.0,  100.0));
		market.put("POP", new Stock("POP", 0.08, 100.0));
		market.put("ALE", new Stock("ALE", 0.23, 60.0));
		market.put("GIN", new Stock("GIN", 0.08, 100.0, 0.02));
		market.put("JOE", new Stock("JOE", 0.13, 250.0));
	}
}

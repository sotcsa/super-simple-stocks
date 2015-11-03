import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class to start.
 * 
 * @author Csaba Soti
 */
public class Main {

	/** Current market, with defined list of stocks. */
	private static Map<String, Stock> market = new HashMap<>();

	/**
	 * The main method - good start point to test project.
	 * It prints out the calculations of all stock values.
	 * 
	 * @param args This program works without parameters
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
		for (Stock stock : market.values()) {
			System.out.format("%s\t", stock.getSymbol());
			System.out.format("%s\t\t\t", stock.calculateDividendYield());
			System.out.format("%s\t\t\t", stock.calculatePERatio());
			System.out.format("%s\n", stock.calculateStockPrice());
		}
	}

	private static void init() {
		new Thread(new TradeSimulator()).start();
		market.put("TEA", new Stock("TEA", BigDecimal.ZERO, new BigDecimal("100")));
		market.put("POP", new Stock("POP", new BigDecimal("0.08"), new BigDecimal("100")));
		market.put("ALE", new Stock("ALE", new BigDecimal("0.23"), new BigDecimal("60")));
		market.put("GIN", new Stock("GIN", new BigDecimal("0.08"), new BigDecimal("100"), new BigDecimal("0.02")));
		market.put("JOE", new Stock("JOE", new BigDecimal("0.13"), new BigDecimal("250")));
	}
}

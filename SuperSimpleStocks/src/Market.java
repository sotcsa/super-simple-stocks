import java.util.HashMap;
import java.util.Map;

public class Market {

	/** The name of market */
	private final String name;

	/** The inner map to stock stocks */
	private final Map<String, Stock> stockMap = new HashMap<String, Stock>();

	/**
	 * Class constructor.
	 * 
	 * @param name market name
	 */
	public Market(String name) {
		this.name = name;
	}

	/**
	 * Puts an stock to the inner map.
	 * 
	 * @param stockSymbol
	 *            using as key
	 * @param stock
	 *            using as value
	 */
	public void put(final String stockSymbol, final Stock stock) {
		stockMap.put(stockSymbol, stock);
	}

	/**
	 * Calculates the index of all contained stocks.
	 * 
	 * @return the GBCE All Share Index
	 */
	public double calculateIndex() {
		double product = 1.0;
		for (Stock stock : stockMap.values()) {
			Double stockPrice = stock.calculateStockPrice();
			if (stockPrice != null) {
				product = product * stockPrice;
			}
		}
		return Math.pow(product, 1.0 / stockMap.size());
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the stockMap
	 */
	public Map<String, Stock> getStockMap() {
		return stockMap;
	}

}

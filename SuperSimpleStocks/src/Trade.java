import java.util.Date;

/**
 * Domain model to store a trade.
 * 
 * @author Csaba Soti
 */
public class Trade {

	/** The stock symbol */
	private String symbol;

	/** Trade time */
	private final Date time = new Date();

	/** The last price */
	private double tickerPrice;

	/** The number of stocks */
	private int quantity;

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 *            the symbol to set
	 */
	public void setSymbol(final String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @return the tickerPrice
	 */
	public double getTickerPrice() {
		return tickerPrice;
	}

	/**
	 * @param tickerPrice
	 *            the tickerPrice to set
	 */
	public void setTickerPrice(double tickerPrice) {
		this.tickerPrice = tickerPrice;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}

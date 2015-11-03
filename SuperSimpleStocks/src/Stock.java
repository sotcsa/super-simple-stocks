/**
 * The stock domain model. It can calculates stock related values from trading
 * system.
 * 
 * @author Csaba Soti
 */
public class Stock {

	/** The stock symbol */
	private final String symbol;
	/** The last dividend */

	private Double lastDividend;

	/** The par value */
	private Double parValue;

	/** The fixed dividend */
	private Double fixedDividend;

	/**
	 * @param symbol
	 */
	public Stock(final String symbol) {
		this.symbol = symbol;
	}

	public Stock(final String symbol, final Double lastDividend, final Double parValue) {
		this(symbol);
		this.lastDividend = lastDividend;
		this.parValue = parValue;
	}

	public Stock(final String symbol, final Double lastDividend, final Double parValue,
			final Double fixedDividend) {
		this(symbol, lastDividend, parValue);
		this.fixedDividend = fixedDividend;
	}

	/**
	 * Calculates he dividend yield.
	 * 
	 * @return calculated stock price
	 */
	public Double calculateDividendYield() {
		// TODO: implement it
		return null;
	}

	/**
	 * Calculates the P/E Ration.
	 * 
	 * @return calculated stock price
	 */
	public Double calculatePERatio() {
		// TODO: implement it
		return null;
	}

	/**
	 * Calculates the Stock Price based on trades recorded in past 15 minutes.
	 * 
	 * @return calculated stock price
	 */
	public Double calculateStockPrice() {
		// TODO: implement it
		return null;
	}

	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param lastDividend
	 *            the lastDividend to set
	 */
	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}

	/**
	 * @param parValue
	 *            the parValue to set
	 */
	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}

	/**
	 * @param fixedDividend
	 *            the fixedDividend to set
	 */
	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

}

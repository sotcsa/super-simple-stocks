import java.math.BigDecimal;

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

	private BigDecimal lastDividend;

	/** The par value */
	private BigDecimal parValue;

	/** The fixed dividend */
	private BigDecimal fixedDividend;

	/**
	 * @param symbol
	 */
	public Stock(final String symbol) {
		this.symbol = symbol;
	}

	public Stock(final String symbol, final BigDecimal lastDividend, final BigDecimal parValue) {
		this(symbol);
		this.lastDividend = lastDividend;
		this.parValue = parValue;
	}

	public Stock(final String symbol, final BigDecimal lastDividend, final BigDecimal parValue,
			final BigDecimal fixedDividend) {
		this(symbol, lastDividend, parValue);
		this.fixedDividend = fixedDividend;
	}

	/**
	 * Calculates he dividend yield.
	 * 
	 * @return calculated stock price
	 */
	public BigDecimal calculateDividendYield() {
		// TODO: implement it
		return null;
	}

	/**
	 * Calculates the P/E Ration.
	 * 
	 * @return calculated stock price
	 */
	public BigDecimal calculatePERatio() {
		// TODO: implement it
		return null;
	}

	/**
	 * Calculates the Stock Price based on trades recorded in past 15 minutes.
	 * 
	 * @return calculated stock price
	 */
	public BigDecimal calculateStockPrice() {
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
	public void setLastDividend(BigDecimal lastDividend) {
		this.lastDividend = lastDividend;
	}

	/**
	 * @param parValue
	 *            the parValue to set
	 */
	public void setParValue(BigDecimal parValue) {
		this.parValue = parValue;
	}

	/**
	 * @param fixedDividend
	 *            the fixedDividend to set
	 */
	public void setFixedDividend(BigDecimal fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

}

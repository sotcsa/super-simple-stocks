/**
 * 
 */
package com.jpmorgan.supersimplestocks.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Utility class to format numbers in proper way.
 * 
 * @author csoti
 */
public class NumberFormatter {

	/**
	 * Renders stock values and index.
	 */
	private static final DecimalFormat df = new DecimalFormat("#,###,###,##0.00");

	/**
	 * Hidden constructor.
	 */
	private NumberFormatter() {
		throw new AssertionError();
	}
	/**
	 * 
	 * @param bigDecimal
	 * @return
	 */
	public static String format(BigDecimal bigDecimal) {
		if (bigDecimal == null) {
			return "  -  ";
		} else {
			return df.format(bigDecimal);
		}
	}

}

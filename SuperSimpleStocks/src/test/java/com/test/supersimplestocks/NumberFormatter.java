/**
 * 
 */
package com.test.supersimplestocks;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author csoti
 *
 */
public class NumberFormatter {

	/**
	 * Renders stock values and index.
	 */
	private static final DecimalFormat df = new DecimalFormat("#,###,###,##0.00");

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

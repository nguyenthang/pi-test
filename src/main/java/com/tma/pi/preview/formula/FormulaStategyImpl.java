package com.tma.pi.preview.formula;

/**
 * @author thangnguyen
 *
 */
public class FormulaStategyImpl implements IFormulaStrategy {

	public FormulaStategyImpl() {
	}

	public double calculate(long startNo, long endNo) {

		double piNo = 0;
		double sNo = 4.0;
		double numerator = 2.0;
		double denominator = 1.0;

		for (long x = startNo; x <= endNo; x++) {

			if (x % 2 == 0) {
				piNo += (sNo / (numerator * x + denominator));
			} else {
				piNo -= (sNo / (numerator * x + denominator));
			}
		}

		return piNo;
	}

}

package com.github.comco.numerals.bg;

public final class Numeral {

	private Numeral() {
	}

	/**
	 * 10^6
	 */
	public static final long MILLION = 1000000;
	
	/**
	 * 10^9
	 */
	public static final long BILLION = 1000000000;

	/**
	 * 10^12
	 */
	public static final long TRILLION = 1000000000000l;
	
	public static boolean isBetween(long number, long min, long maxExcluded) {
		return min <= number && number < maxExcluded;
	}

	public static boolean isOnes(long number) {
		return 0 <= number && number < 10;
	}

	public static boolean isTeens(long number) {
		return 10 < number && number < 20;
	}

	public static boolean isTens(long number) {
		return 0 < number && number < 100 && number % 10 == 0;
	}

	public static boolean isHundreds(long number) {
		return 0 < number && number < 1000 && number % 100 == 0;
	}

	public static boolean isThousands(long number) {
		return 0 < number && number < MILLION && number % 1000 == 0;
	}

	public static boolean isMillions(long number) {
		return 0 < number && number < BILLION && number % MILLION == 0;
	}

	public static long getTensPart(long number) {
		assert number >= 0;
		return (number / 10) * 10;
	}

	public static long getOnesPart(long number) {
		assert number >= 0;
		return number % 10;
	}

	public static long getHundredsPart(long number) {
		assert number >= 0;
		return (number / 100) * 100;
	}

	public static long getHundredsRemainder(long number) {
		assert number >= 0;
		return number % 100;
	}
	
	public static long getThousands(long number) {
		assert number >= 0;
		return number / 1000;
	}

	public static long getThousandsPart(long number) {
		assert number >= 0;
		return getThousands(number) * 1000;
	}
	
	public static long getThousandsRemainder(long number) {
		assert number >= 0;
		return number % 1000;
	}
	
	public static long getMillions(long number) {
		assert number >= 0;
		return number / Numeral.MILLION;
	}
	
	public static long getMillionsPart(long number) {
		assert number >= 0;
		return getMillions(number) * Numeral.MILLION;
	}
	
	public static long getMillionsRemainder(long number) {
		assert number >= 0;
		return number % Numeral.MILLION;
	}
}

package com.github.comco.numerals.bg;

import com.github.comco.numerals.bg.GrammaticalGender.Type;

public enum PrimaryNumeral {
	
	/**
	 * Word for 10^3.
	 */
	THOUSAND(longpow(10, 3), "хиляда", "хиляди", Type.FEMININE, null), 
	
	/**
	 * Word for 10^6.
	 */
	MILLION(longpow(10, 6), "милион", "милиона", Type.MASCULINE, THOUSAND), 
	
	/**
	 * Word for 10^9.
	 */
	BILLION(longpow(10, 9), "милиард", "милиарда", Type.MASCULINE, MILLION), 
	
	/**
	 * Word for 10^12.
	 */
	TRILLION(longpow(10, 12), "трилион", "трилиона", Type.MASCULINE, BILLION),

	/**
	 * Word for 10^15.
	 */
	QUADRILLION(longpow(10, 15), "квадрилион", "квадрилиона", Type.MASCULINE, TRILLION),
	
	/**
	 * Word for 10^18.
	 */
	QUINTILLION(longpow(10, 18), "квинтилион", "квинтилиона", Type.MASCULINE, QUADRILLION);
	
	/**
	 * The value of this numeral.
	 */
	public final long value;
	
	/**
	 * Word for this numeral in singular.
	 */
	public final String singular;
	
	/**
	 * Word for this numeral in plural.
	 */
	public final String plural;
	
	/**
	 * The gender of this numeral.
	 */
	public final GrammaticalGender.Type gender;
	
	/**
	 * The parent numeral of this numeral.
	 */
	public final PrimaryNumeral parent;
	
	PrimaryNumeral(long value, String singular, String plural, Type gender, PrimaryNumeral parent) {
		this.value = value;
		this.singular = singular;
		this.plural = plural;
		this.gender = gender;
		this.parent = parent;
	}
	
	public long getQuotient(long number) {
		return number / value;
	}
	
	public long getRemainder(long number) {
		return number % value;
	}
	
	public long getPart(long number) {
		return getQuotient(number) * value;
	}
	
	private static long longpow(long base, long power) {
		long result = 1;
		for (int i = 0; i < power; ++i) {
			result *= base;
		}
		return result;
	}
}

package com.github.comco.numerals.bg;

import com.github.comco.numerals.bg.GrammaticalGender.Type;

public class NumeralConverter {

	public static final String WEAK_SEPARATOR = " ";

	public static final String STRONG_SEPARATOR = " и ";

	private final GrammaticalGender gender;

	private NumeralConverter(GrammaticalGender gender) {
		this.gender = gender;
	}

	private NumeralConverter(NumeralConverter converter,
			GrammaticalGender gender) {
		this.gender = gender;
	}

	String toNumeralBelowHundred(long number) {
		assert Numeral.isBetween(number, 0, 100);
		String numeral;
		if (Numeral.isOnes(number)) {
			numeral = gender.onesName(number);
		} else if (Numeral.isTens(number)) {
			numeral = gender.tensName(number);
		} else if (Numeral.isTeens(number)) {
			numeral = gender.teensName(number);
		} else {
			long tensPart = Numeral.getTensPart(number);
			long onesPart = Numeral.getOnesPart(number);
			numeral = gender.tensName(tensPart) + STRONG_SEPARATOR
					+ gender.onesName(onesPart);
		}
		return numeral;
	}

	String toNumeralPartBelowHundred(long number) {
		assert Numeral.isBetween(number, 0, 100);
		String numeralPart;
		if (number == 0) {
			numeralPart = "";
		} else if (Numeral.isOnes(number) || Numeral.isTeens(number) || Numeral.isTens(number)) {
			numeralPart = STRONG_SEPARATOR + toNumeralBelowHundred(number);
		} else {
			numeralPart = WEAK_SEPARATOR + toNumeralBelowHundred(number);
		}
		return numeralPart;
	}

	String toNumeralBelowThousand(long number) {
		assert Numeral.isBetween(number, 0, 1000);
		String numeral;
		if (number < 100) {
			numeral = toNumeralBelowHundred(number);
		} else {
			long hundredsPart = Numeral.getHundredsPart(number);
			long hundredsRemainder = Numeral.getHundredsRemainder(number);
			numeral = gender.getHundredsName(hundredsPart)
					+ toNumeralPartBelowHundred(hundredsRemainder);
		}
		return numeral;
	}

	String toNumeralPartBelowThousand(long number) {
		assert Numeral.isBetween(number, 0, 1000);
		String numeralPart;
		if (number < 100) {
			numeralPart = toNumeralPartBelowHundred(number);
		} else if (Numeral.isHundreds(number)) {
			numeralPart = STRONG_SEPARATOR + toNumeralBelowThousand(number);
		} else {
			numeralPart = WEAK_SEPARATOR + toNumeralBelowThousand(number);
		}
		return numeralPart;
	}
	
	String toNumeralBelowMillion(long number) {
		assert Numeral.isBetween(number, 0, Numeral.MILLION);
		String numeral;
		if (number < 1000) {
			numeral = toNumeralBelowThousand(number);
		} else if (number < 2000) {
			numeral = PrimaryNumeral.THOUSAND.singular
					+ toNumeralPartBelowThousand(number - 1000);
		} else {
			NumeralConverter thousandsConverter = getConverterFor(PrimaryNumeral.THOUSAND.gender);
			long thousand = Numeral.getThousands(number);
			long thousandRemainder = Numeral.getThousandsRemainder(number);
			numeral = thousandsConverter.toNumeralBelowThousand(thousand)
					+ WEAK_SEPARATOR + PrimaryNumeral.THOUSAND.plural
					+ toNumeralPartBelowThousand(thousandRemainder);
		}
		return numeral;
	
	}

	String toNumeralPartBelowMillion(long number) {
		assert Numeral.isBetween(number, 0, Numeral.MILLION);
		String numeralPart;
		if (number < 1000) {
			numeralPart = toNumeralPartBelowThousand(number);
		} else if (Numeral.isThousands(number)) {
			numeralPart = STRONG_SEPARATOR + toNumeralBelowMillion(number);
		} else {
			numeralPart = WEAK_SEPARATOR + toNumeralBelowMillion(number);
		}
		return numeralPart;
	}

	String toNumeralBelow(PrimaryNumeral bound, long number) {
		assert Numeral.isBetween(number, 0, bound.value);
		String numeral;
		if (bound == PrimaryNumeral.MILLION) {
			numeral = toNumeralBelowMillion(number);
		} else if (number < bound.parent.value) {
			numeral = toNumeralBelow(bound.parent, number);			
		} else {
			NumeralConverter converter = getConverterFor(bound.parent.gender);
			long quotient = bound.parent.getQuotient(number);
			String word;
			if (quotient < 2) {
				word = bound.parent.singular;
			} else {
				word = bound.parent.plural;
			}
			long remainder = bound.parent.getRemainder(number);
			numeral = converter.toNumeralBelow(bound.parent, quotient)
					+ WEAK_SEPARATOR + word
					+ toNumeralPartBelow(bound.parent, remainder);
		}
		return numeral;
	}
	

	String toNumeralPartBelow(PrimaryNumeral bound, long number) {
		assert Numeral.isBetween(number, 0, bound.value);
		String numeralPart;
		if (bound == PrimaryNumeral.MILLION) {
			numeralPart = toNumeralPartBelowMillion(number);
		} else if (number < bound.parent.value) {
			numeralPart = toNumeralPartBelow(bound.parent, number);
		} else if (bound.parent.getRemainder(number) == 0) {
			numeralPart = STRONG_SEPARATOR + toNumeralBelow(bound, number);
		} else {
			numeralPart = WEAK_SEPARATOR + toNumeralBelow(bound, number);
		}
		return numeralPart;
	}

	String toNumeralBelowBillion(long number) {
		assert Numeral.isBetween(number, 0, Numeral.BILLION);
		String numeral;
		if (number < Numeral.MILLION) {
			numeral = toNumeralBelowMillion(number);
		} else {
			NumeralConverter millionsConverter = getConverterFor(PrimaryNumeral.MILLION.gender);
			long millions = Numeral.getMillions(number);
			long millionsRemainder = Numeral.getMillionsRemainder(number);
			String millionWord;
			if (number < 2 * Numeral.MILLION) {
				millionWord = PrimaryNumeral.MILLION.singular;
			} else {
				millionWord = PrimaryNumeral.MILLION.plural;
			}
			numeral = millionsConverter.toNumeralBelowThousand(millions)
					+ WEAK_SEPARATOR + millionWord
					+ toNumeralPartBelowMillion(millionsRemainder);
		}
		return numeral;
	}
	
	String toNumeralPartBelowBillion(long number) {
		assert Numeral.isBetween(number, 0, Numeral.BILLION);
		String numeralPart;
		if (number < Numeral.MILLION) {
			numeralPart = toNumeralPartBelowMillion(number);
		} else if (Numeral.isMillions(number)) {
			numeralPart = STRONG_SEPARATOR + toNumeralBelowBillion(number);
		} else {
			numeralPart = WEAK_SEPARATOR + toNumeralBelowBillion(number);
		}
		return numeralPart;
	}
	
	String toNumeralBelowTrillion(long number) {
		assert Numeral.isBetween(number, 0, Numeral.TRILLION);
		String numeral;
		if (number < Numeral.BILLION) {
			numeral = toNumeralBelowBillion(number);
		} else {
			NumeralConverter billionConverter = getConverterFor(PrimaryNumeral.BILLION.gender);
			long millions = Numeral.getMillions(number);
			long millionsRemainder = Numeral.getMillionsRemainder(number);
			String millionWord;
			if (number < 2 * Numeral.MILLION) {
				millionWord = PrimaryNumeral.MILLION.singular;
			} else {
				millionWord = PrimaryNumeral.MILLION.plural;
			}
			numeral = billionConverter.toNumeralBelowThousand(millions)
					+ WEAK_SEPARATOR + millionWord
					+ toNumeralPartBelowMillion(millionsRemainder);
		}
		return numeral;
	}

	public String toNumeral(long number) {
		if (Numeral.isBetween(number, 0, PrimaryNumeral.QUINTILLION.value)) {
			return toNumeralBelow(PrimaryNumeral.QUINTILLION, number);
		} else {
			throw new IllegalArgumentException(
					"Argument must be between zero and below a quintillion (10^18). The given number was: "
							+ number);
		}
	}

	public NumeralConverter deriveWith(Type gender) {
		return new NumeralConverter(this, GrammaticalGender.getInstance(gender));
	}

	public static NumeralConverter getConverterFor(Type gender) {
		return new NumeralConverter(GrammaticalGender.getInstance(gender));
	}
}

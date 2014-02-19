package com.github.comco.numerals.bg;

/**
 * Represents the grammatical gender category in Bulgarian language.
 * 
 * @author comco
 * 
 */
public abstract class GrammaticalGender {

	public static enum Type {
		MASCULINE, FEMININE, NEUTER
	}

	/**
	 * Names of the numbers 10 + k, where k = 0, 1, ..., 9.
	 */
	private static final String[] TEENS = { "десет", "единадесет",
			"дванадесет", "тринадесет", "четиринадесет", "петнадесет",
			"шестнадесет", "седемнадесет", "осемнадесет", "деветнадесет" };

	/**
	 * Names of the numbers 10 * k, where k = 0, 1, ..., 9.
	 */
	private static final String[] TENS = { "нула", "десет", "двадесет",
			"тридесет", "четиридесет", "петдесет", "шестдесет", "седемдесет",
			"осемдесет", "деветдесет" };

	/**
	 * Names of the numbers 100 * k, where k = 0, 1, ..., 9.
	 */
	private static final String[] HUNDREDS = { "нула", "сто", "двеста",
			"триста", "четиристотин", "петстотин", "шестстотин", "седемстотин",
			"осемстотин", "деветстотин" };

	private static final MasculineGrammaticalGender MASCULINE_GENDER = new MasculineGrammaticalGender();

	private static final FeminineGrammaticalGender FEMININE_GENDER = new FeminineGrammaticalGender();

	private static final NeuterGrammaticalGender NEUTER_GENDER = new NeuterGrammaticalGender();

	public static final GrammaticalGender getInstance(Type type) {
		switch (type) {
		case MASCULINE:
			return MASCULINE_GENDER;
		case FEMININE:
			return FEMININE_GENDER;
		case NEUTER:
			return NEUTER_GENDER;
		default:
			throw new IllegalArgumentException(
					"Illegal grammatical gender type for construction of grammatical gender: "
							+ type);
		}
	}

	protected GrammaticalGender() {
	}

	public String getOnesName(long number) {
		if (Numeral.isOnes(number)) {
			return onesName(number);
		} else {
			throw new IllegalArgumentException(
					"Ones are only the numbers 0, 1, ..., 9."
							+ " The given number was: " + number);
		}
	}

	public String getTeensName(long number) {
		if (Numeral.isTeens(number)) {
			return teensName(number);
		} else {
			throw new IllegalArgumentException(
					"Teens are only the numbers 11, 12, ..., 19."
							+ " The given number was: " + number);
		}
	}

	public String getTensName(long number) {
		if (Numeral.isTens(number)) {
			return tensName(number);
		} else {
			throw new IllegalArgumentException(
					"Tens are only the numbers 10, 20, ..., 90."
							+ " The given number was: " + number);
		}
	}

	public String getHundredsName(long number) {
		if (Numeral.isHundreds(number)) {
			return hundredsName(number);
		} else {
			throw new IllegalArgumentException(
					"Hundreds are only the numbers 100, 200, ..., 900."
							+ " The given number was: " + number);
		}
	}

	abstract String onesName(long number);

	String teensName(long number) {
		assert Numeral.isTeens(number);
		return TEENS[(int) (number - 10)];
	}

	String tensName(long number) {
		assert Numeral.isTens(number);
		return TENS[(int) (number / 10)];
	}

	String hundredsName(long number) {
		assert Numeral.isHundreds(number);
		return HUNDREDS[(int) (number / 100)];
	}
}

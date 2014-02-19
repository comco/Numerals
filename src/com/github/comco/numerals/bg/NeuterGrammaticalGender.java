package com.github.comco.numerals.bg;

class NeuterGrammaticalGender extends GrammaticalGender {

	private static final String[] ONES = { "нула", "едно", "двe", "три",
			"четири", "пет", "шест", "седем", "осем", "девет" };

	@Override
	String onesName(long number) {
		assert Numeral.isOnes(number);
		return ONES[(int) number];
	}
}

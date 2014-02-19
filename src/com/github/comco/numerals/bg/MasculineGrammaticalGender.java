package com.github.comco.numerals.bg;

class MasculineGrammaticalGender extends GrammaticalGender {

	private static final String[] ONES = { "нула", "един", "два", "три",
			"четири", "пет", "шест", "седем", "осем", "девет" };

	@Override
	String onesName(long number) {
		assert Numeral.isOnes(number);
		return ONES[(int) number];
	}
}

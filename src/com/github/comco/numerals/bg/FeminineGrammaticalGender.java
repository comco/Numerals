package com.github.comco.numerals.bg;

class FeminineGrammaticalGender extends GrammaticalGender {

	private static final String[] ONES = { "нула", "една", "двe", "три",
			"четири", "пет", "шест", "седем", "осем", "девет" };

	@Override
	String onesName(long number) {
		assert Numeral.isOnes(number);
		return ONES[(int) number];
	}
}

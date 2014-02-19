package com.github.comco.numerals;

import com.github.comco.numerals.bg.GrammaticalGender.Type;
import com.github.comco.numerals.bg.NumeralConverter;

public class Program {
	public static void main(String[] args) {
		NumeralConverter converter = NumeralConverter.getConverterFor(Type.MASCULINE);
		System.out.println(converter.toNumeral(3643673642734718L));
	}
}

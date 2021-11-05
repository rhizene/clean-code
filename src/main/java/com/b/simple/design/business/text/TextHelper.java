package com.b.simple.design.business.text;

import java.util.Arrays;

public class TextHelper {

	public String swapLastTwoCharacters(String str) {
		final int charactersToReverse = 2;
		final int length = str.length();
		if(length < charactersToReverse) return str;

		final String prefix = str.substring(0, length - charactersToReverse);
		final char[] lastTwo = str.substring(prefix.length(), length)
				.toCharArray();

		return prefix + lastTwo[1] + lastTwo[0];
	}

	public String truncateAInFirst2Positions(String str) {
		final int length = str.length();
		final int charToTruncate = 2;
		final String targetChar = "A";

		if (length <= charToTruncate) return  str.replaceAll(targetChar, "");

		final String truncatedString = str.substring(0, charToTruncate)
										  .replaceAll(targetChar, "");
		final  String suffix = str.substring(charToTruncate);
		return truncatedString + suffix;
	}
}

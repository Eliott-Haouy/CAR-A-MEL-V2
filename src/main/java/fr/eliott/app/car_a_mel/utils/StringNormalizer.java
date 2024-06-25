package fr.eliott.app.car_a_mel.utils;

import java.text.Normalizer;

/**
 * String normalizer
 */
public class StringNormalizer {

	/**
	 * Replace all accents in given String by basic char
	 * 
	 * @param input
	 * @return altered String or null
	 */
	public static String removeAccents(String input) {
		return normalize(input).replaceAll("\\p{M}", "");
	}

	private static String normalize(String input) {
		return input == null ? null : Normalizer.normalize(input, Normalizer.Form.NFKD);
	}
}

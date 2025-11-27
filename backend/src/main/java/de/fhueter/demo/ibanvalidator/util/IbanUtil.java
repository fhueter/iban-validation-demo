package de.fhueter.demo.ibanvalidator.util;

import org.apache.commons.validator.routines.IBANValidator;

public class IbanUtil {

	private IbanUtil() {
		// no instances of this util class
	}

	public static String toCompactFormat(String ibanToClean) {
		return ibanToClean.replaceAll("\\s+", "").toUpperCase();
	}

	public static boolean isValid(String iban) {
		return IBANValidator.getInstance().isValid(toCompactFormat(iban));
	}
}

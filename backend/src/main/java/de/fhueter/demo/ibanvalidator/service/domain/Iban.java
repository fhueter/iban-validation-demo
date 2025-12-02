package de.fhueter.demo.ibanvalidator.service.domain;

import de.fhueter.demo.ibanvalidator.service.exception.IbanParseException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.IBANValidator;
import org.jspecify.annotations.Nullable;

@Getter
public class Iban {

	private final String compactValue;

	private Iban(String compactValue) {
		this.compactValue = compactValue;
	}

	public static Iban parse(String iban) throws IbanParseException {
		if (StringUtils.isEmpty(iban)) {
			throw new IbanParseException("IBAN darf nicht leer sein");
		}
		String compactIbanValue = toCompactValue(iban);
		if (!IBANValidator.getInstance().isValid(compactIbanValue)) {
			throw new IbanParseException("IBAN ist ungültig");
		}
		return new Iban(compactIbanValue);
	}

	private static String toCompactValue(String iban) {
		return iban.replaceAll("\\s+", "").toUpperCase();
	}

	// TODO support for international banks
	public @Nullable String getBankCode() {
		if (compactValue.startsWith("DE")) {
			return compactValue.substring(4, 12);
		}
		return null;
	}
}

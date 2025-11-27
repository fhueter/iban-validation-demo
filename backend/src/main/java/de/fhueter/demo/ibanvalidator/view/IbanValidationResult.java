package de.fhueter.demo.ibanvalidator.view;

import de.fhueter.demo.ibanvalidator.model.Bank;
import org.jspecify.annotations.Nullable;

public record IbanValidationResult(boolean valid, @Nullable String bic, @Nullable String bankName) {

	public static IbanValidationResult valid(@Nullable Bank bank) {
		if (bank == null) {
			return new IbanValidationResult(true, null, null);
		}
		return new IbanValidationResult(true, bank.getBic(), bank.getName());
	}

	public static IbanValidationResult invalid() {
		return new IbanValidationResult(false, null, null);
	}
}

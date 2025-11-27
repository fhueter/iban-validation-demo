package de.fhueter.demo.ibanvalidator.service;

import de.fhueter.demo.ibanvalidator.util.IbanUtil;
import de.fhueter.demo.ibanvalidator.view.IbanValidationResult;
import org.springframework.stereotype.Service;

@Service
public class BankService {

	private final BankResolver bankResolver;

	public BankService(BankResolver bankResolver) {
		this.bankResolver = bankResolver;
	}

	public IbanValidationResult validateIban(String ibanToCheck) {
		String compactIban = IbanUtil.toCompactFormat(ibanToCheck);
		if (!IbanUtil.isValid(compactIban)) {
			return IbanValidationResult.invalid();
		}
		return IbanValidationResult.valid(bankResolver.findBankByIban(compactIban));
	}

}

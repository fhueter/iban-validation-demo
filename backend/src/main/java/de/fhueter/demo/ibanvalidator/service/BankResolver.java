package de.fhueter.demo.ibanvalidator.service;

import de.fhueter.demo.ibanvalidator.model.Bank;
import de.fhueter.demo.ibanvalidator.repository.BankRepository;
import de.fhueter.demo.ibanvalidator.util.IbanUtil;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BankResolver {

	private final BankRepository bankRepository;

	public BankResolver(BankRepository bankRepository) {
		this.bankRepository = bankRepository;
	}

	@Nullable String extractBankCodeFromIban(String iban) {
		String compactIban = IbanUtil.toCompactFormat(iban);
		if (!IbanUtil.isValid(compactIban)) {
			return null;
		}
		if (compactIban.startsWith("DE")) {
			return compactIban.substring(4, 12);
		}
		return null;
	}

	@Transactional(readOnly = true)
	public @Nullable Bank findBankByIban(String iban) {
		String bankCode = extractBankCodeFromIban(iban);
		if (bankCode == null) {
			return null;
		}
		return bankRepository.findByBankCode(bankCode);
	}
}

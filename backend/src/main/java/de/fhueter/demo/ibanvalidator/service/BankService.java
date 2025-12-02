package de.fhueter.demo.ibanvalidator.service;

import de.fhueter.demo.ibanvalidator.controller.exception.ApiConflictException;
import de.fhueter.demo.ibanvalidator.model.Bank;
import de.fhueter.demo.ibanvalidator.repository.BankRepository;
import de.fhueter.demo.ibanvalidator.service.domain.Iban;
import de.fhueter.demo.ibanvalidator.service.exception.IbanParseException;
import de.fhueter.demo.ibanvalidator.view.BankView;
import de.fhueter.demo.ibanvalidator.view.IbanValidationResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {

	private final BankRepository bankRepository;

	public BankService(BankRepository bankRepository) {
		this.bankRepository = bankRepository;
	}

	@Transactional(readOnly = true)
	public IbanValidationResult validateIban(String ibanToCheck) {
		Iban iban;
		try {
			iban = Iban.parse(ibanToCheck);
		} catch (IbanParseException _) {
			return IbanValidationResult.invalid();
		}
		String bankCode = iban.getBankCode();
		if (bankCode == null) {
			return IbanValidationResult.valid(null);
		}
		return IbanValidationResult.valid(bankRepository.findByBankCode(bankCode));
	}

	@Transactional
	public BankView createBank(BankView bankView) {
		Bank bank = bankRepository.findByBankCode(bankView.getBankCode());
		if (bank != null) {
			throw new ApiConflictException("Eine Bank mit dieser BLZ existiert bereits.");
		}
		Bank newBank = bankRepository.save(bankView.toBankEntity());
		return BankView.of(newBank);
	}
}

package de.fhueter.demo.ibanvalidator.service;

import de.fhueter.demo.ibanvalidator.exception.ApiConflictException;
import de.fhueter.demo.ibanvalidator.model.Bank;
import de.fhueter.demo.ibanvalidator.repository.BankRepository;
import de.fhueter.demo.ibanvalidator.util.IbanUtil;
import de.fhueter.demo.ibanvalidator.view.BankView;
import de.fhueter.demo.ibanvalidator.view.IbanValidationResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {

	private final BankRepository bankRepository;

	private final BankResolver bankResolver;

	public BankService(BankRepository bankRepository, BankResolver bankResolver) {
		this.bankRepository = bankRepository;
		this.bankResolver = bankResolver;
	}

	public IbanValidationResult validateIban(String ibanToCheck) {
		String compactIban = IbanUtil.toCompactFormat(ibanToCheck);
		if (!IbanUtil.isValid(compactIban)) {
			return IbanValidationResult.invalid();
		}
		return IbanValidationResult.valid(bankResolver.findBankByIban(compactIban));
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

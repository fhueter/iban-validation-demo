package de.fhueter.demo.ibanvalidator.service;

import de.fhueter.demo.ibanvalidator.SpringTest;
import de.fhueter.demo.ibanvalidator.model.Bank;
import de.fhueter.demo.ibanvalidator.repository.BankRepository;
import de.fhueter.demo.ibanvalidator.view.IbanValidationResult;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class BankServiceIT extends SpringTest {

	@Autowired
	BankService bankService;

	@Autowired
	BankRepository bankRepository;

	@BeforeEach
	void setUp() {
		bankRepository.save(new Bank("37040044", "COBADEFFXXX", "Commerzbank Köln"));
	}

	@AfterEach
	void tearDown() {
		bankRepository.deleteAll();
	}

	@Test
	void validateIban_returnsBankForMatchingBankCode() {
		IbanValidationResult result = bankService.validateIban("de89 3704 0044 0532 0130 00  ");

		SoftAssertions.assertSoftly(s -> {
			s.assertThat(result.valid()).isTrue();
			s.assertThat(result.bankName()).isEqualTo("Commerzbank Köln");
			s.assertThat(result.bic()).isEqualTo("COBADEFFXXX");
		});
	}

	@Test
	void validateIban_returnsNoBankForUnknownGermanBankCode() {
		IbanValidationResult result = bankService.validateIban("DE75512108001245126199");

		SoftAssertions.assertSoftly(s -> {
			s.assertThat(result.valid()).isTrue();
			s.assertThat(result.bankName()).isNull();
			s.assertThat(result.bic()).isNull();
		});
	}

	@Test
	void validateIban_returnsNoBankForFrenchBankCode() {
		IbanValidationResult result = bankService.validateIban("FR7630006000011234567890189");

		SoftAssertions.assertSoftly(s -> {
			s.assertThat(result.valid()).isTrue();
			s.assertThat(result.bankName()).isNull();
			s.assertThat(result.bic()).isNull();
		});
	}

	@Test
	void validateIban_failsWhenInvalid() {
		IbanValidationResult result = bankService.validateIban("DE00512108001245126199");

		SoftAssertions.assertSoftly(s -> {
			s.assertThat(result.valid()).isFalse();
			s.assertThat(result.bankName()).isNull();
			s.assertThat(result.bic()).isNull();
		});
	}
}
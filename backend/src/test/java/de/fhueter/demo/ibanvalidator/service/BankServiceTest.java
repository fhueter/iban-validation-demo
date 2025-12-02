package de.fhueter.demo.ibanvalidator.service;

import de.fhueter.demo.ibanvalidator.UnitTest;
import de.fhueter.demo.ibanvalidator.model.Bank;
import de.fhueter.demo.ibanvalidator.repository.BankRepository;
import de.fhueter.demo.ibanvalidator.view.IbanValidationResult;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.doReturn;

class BankServiceTest extends UnitTest {

	@Mock
	BankRepository bankRepository;

	@InjectMocks
	BankService bankService;

	@Test
	void returnsBankForMatchingBankCode() {
		Bank coba = new Bank("37040044", "COBADEFFXXX", "Commerzbank Köln");
		doReturn(coba).when(bankRepository).findByBankCode("37040044");

		IbanValidationResult result = bankService.validateIban("de89 3704 0044 0532 0130 00  ");

		SoftAssertions.assertSoftly(s -> {
			s.assertThat(result.valid()).isTrue();
			s.assertThat(result.bankName()).isEqualTo("Commerzbank Köln");
			s.assertThat(result.bic()).isEqualTo("COBADEFFXXX");
		});
	}

	@Test
	void returnsNoBankForUnknownGermanBankCode() {
		IbanValidationResult result = bankService.validateIban("DE75512108001245126199");

		SoftAssertions.assertSoftly(s -> {
			s.assertThat(result.valid()).isTrue();
			s.assertThat(result.bankName()).isNull();
			s.assertThat(result.bic()).isNull();
		});
	}

	@Test
	void returnsNoBankForFrenchBankCode() {
		IbanValidationResult result = bankService.validateIban("FR7630006000011234567890189");

		SoftAssertions.assertSoftly(s -> {
			s.assertThat(result.valid()).isTrue();
			s.assertThat(result.bankName()).isNull();
			s.assertThat(result.bic()).isNull();
		});
	}

	@Test
	void failsWhenInvalid() {
		IbanValidationResult result = bankService.validateIban("DE00512108001245126199");

		SoftAssertions.assertSoftly(s -> {
			s.assertThat(result.valid()).isFalse();
			s.assertThat(result.bankName()).isNull();
			s.assertThat(result.bic()).isNull();
		});
	}
}
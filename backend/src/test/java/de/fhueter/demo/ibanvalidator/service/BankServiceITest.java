package de.fhueter.demo.ibanvalidator.service;

import de.fhueter.demo.ibanvalidator.SpringTest;
import de.fhueter.demo.ibanvalidator.exception.ApiConflictException;
import de.fhueter.demo.ibanvalidator.model.Bank;
import de.fhueter.demo.ibanvalidator.repository.BankRepository;
import de.fhueter.demo.ibanvalidator.view.BankView;
import de.fhueter.demo.ibanvalidator.view.IbanValidationResult;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class BankServiceITest extends SpringTest {

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

	@Nested
	class CreateBankITest {

		@Test
		void successful() {
			BankView bankView = BankView.builder().bankCode("10070000").bic("DEUTDEBBXXX").name("Deutsche Bank").build();

			BankView savedBankView = bankService.createBank(bankView);
			Bank savedBank = bankRepository.findByBankCode("10070000");

			assertThat(savedBank).isNotNull();
			SoftAssertions.assertSoftly(s -> {
				s.assertThat(savedBank.getId()).isNotNull();
				s.assertThat(savedBank.getBankCode()).isEqualTo("10070000");
				s.assertThat(savedBank.getBic()).isEqualTo("DEUTDEBBXXX");
				s.assertThat(savedBank.getName()).isEqualTo("Deutsche Bank");
				s.assertThat(savedBankView.getId()).isEqualTo(savedBank.getId());
			});
		}

		@Test
		void throwsExceptionWhenBankCodeInUse() {
			BankView bankView = BankView.builder().bankCode("37040044").bic("DEUTDEBBXXX").name("Commerzbank Köln 2").build();

			assertThatExceptionOfType(ApiConflictException.class).isThrownBy(() -> bankService.createBank(bankView));
		}
	}

	@Nested
	class ValidateIbanITest {

		@Test
		void returnsBankForMatchingBankCode() {
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
}
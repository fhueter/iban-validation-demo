package de.fhueter.demo.ibanvalidator.service;

import de.fhueter.demo.ibanvalidator.UnitTest;
import de.fhueter.demo.ibanvalidator.model.Bank;
import de.fhueter.demo.ibanvalidator.repository.BankRepository;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;

class BankResolverTest extends UnitTest {

	@Mock
	BankRepository bankRepository;

	@InjectMocks
	BankResolver bankResolver;

	static Stream<Arguments> extractBankCodeFromIbanData() {
		return Stream.of(
			arguments("de89 3704 0044 0532 0130 00  ", "37040044"),
			arguments("DE89370400440532013000", "37040044"),
			arguments("GB29NWBK60161331926819", null),
			arguments("DE1234", null),
			arguments("", null)
		);
	}

	@ParameterizedTest
	@MethodSource("extractBankCodeFromIbanData")
	void extractBankCodeFromIban(String ibanToCheck, @Nullable String expectedBankCode) {
		assertThat(bankResolver.extractBankCodeFromIban(ibanToCheck)).isEqualTo(expectedBankCode);
	}

	@Test
	void findBankByIban_success() {
		Bank coba = new Bank("37040044", "COBADEFFXXX", "Commerzbank Köln");
		doReturn(coba).when(bankRepository).findByBankCode("37040044");

		Bank bank = bankResolver.findBankByIban("DE89370400440532013000");

		assertThat(bank).isEqualTo(coba);
	}

	@Test
	void findBankByIban_returnsNullForUnknownBankCode() {
		Bank bank = bankResolver.findBankByIban("DE89370400440532013000");

		assertThat(bank).isNull();
	}

	@Test
	void findBankByIban_returnsNullForInvalidIban() {
		Bank bank = bankResolver.findBankByIban("DE123456788");

		assertThat(bank).isNull();
	}
}
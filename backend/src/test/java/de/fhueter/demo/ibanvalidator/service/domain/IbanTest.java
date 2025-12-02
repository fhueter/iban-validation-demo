package de.fhueter.demo.ibanvalidator.service.domain;

import de.fhueter.demo.ibanvalidator.UnitTest;
import de.fhueter.demo.ibanvalidator.service.exception.IbanParseException;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class IbanTest extends UnitTest {

	static Stream<Arguments> getBankCodeData() {
		return Stream.of(
			arguments("DE89370400440532013000", "37040044"),
			arguments("GB29NWBK60161331926819", null)
		);
	}

	@ParameterizedTest
	@MethodSource("getBankCodeData")
	void getBankCode(String ibanToCheck, @Nullable String expectedBankCode) throws IbanParseException {
		assertThat(Iban.parse(ibanToCheck).getBankCode()).isEqualTo(expectedBankCode);
	}

	@Nested
	class ParseTest {

		static Stream<Arguments> parseIbanData() {
			return Stream.of(
				arguments(" de89 3704 0044 0532 0130 00 ", "DE89370400440532013000"),
				arguments("DE89370400440532013000", "DE89370400440532013000"),
				arguments("GB29NWBK60161331926819", "GB29NWBK60161331926819")
			);
		}

		@ParameterizedTest
		@MethodSource("parseIbanData")
		void returnsIbanWhenIbanIsValid(String ibanToCheck, String compactValue) throws IbanParseException {
			assertThat(Iban.parse(ibanToCheck).getCompactValue()).isEqualTo(compactValue);
		}

		@Test
		void throwsExceptionWhenIbanIsEmpty() {
			assertThatExceptionOfType(IbanParseException.class).isThrownBy(() -> Iban.parse(""));
		}

		@Test
		void throwsExceptionWhenIbanIsInvalid() {
			assertThatExceptionOfType(IbanParseException.class).isThrownBy(() -> Iban.parse("DE1234"));
		}
	}
}
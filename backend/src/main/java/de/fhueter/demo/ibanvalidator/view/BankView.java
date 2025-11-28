package de.fhueter.demo.ibanvalidator.view;

import de.fhueter.demo.ibanvalidator.model.Bank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.jspecify.annotations.Nullable;

@Data
@Builder
@AllArgsConstructor
public class BankView {

	@Nullable Long id;

	@Pattern(regexp = "^\\d{8}$")
	String bankCode;

	@Pattern(regexp = "^[a-zA-Z0-9]{11}$")
	String bic;

	@NotBlank
	String name;

	public static BankView of(Bank bank) {
		return BankView.builder()
			.id(bank.getId())
			.bankCode(bank.getBankCode())
			.bic(bank.getBic())
			.name(bank.getName())
			.build();
	}

	public Bank toBankEntity() {
		return Bank.builder()
			.bankCode(bankCode)
			.bic(bic)
			.name(name)
			.build();
	}
}

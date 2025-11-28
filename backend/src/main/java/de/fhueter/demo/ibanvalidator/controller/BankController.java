package de.fhueter.demo.ibanvalidator.controller;

import de.fhueter.demo.ibanvalidator.service.BankService;
import de.fhueter.demo.ibanvalidator.view.IbanValidationResult;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankController {

	final BankService bankService;

	public BankController(BankService bankService) {
		this.bankService = bankService;
	}

	@PostMapping("/validate-iban")
	public IbanValidationResult validateIban(@RequestBody @Validated IbanValidationRequest ibanToValidate) {
		return bankService.validateIban(ibanToValidate.iban);
	}

	public record IbanValidationRequest(@NotBlank String iban) {

	}
}

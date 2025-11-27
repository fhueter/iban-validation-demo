package de.fhueter.demo.ibanvalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IbanValidatorApplication {

	private IbanValidatorApplication() {
		// no instances of this application class
	}

	static void main(String[] args) {
		SpringApplication.run(IbanValidatorApplication.class, args);
	}

}

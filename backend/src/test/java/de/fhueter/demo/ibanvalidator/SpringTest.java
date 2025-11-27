package de.fhueter.demo.ibanvalidator;

import org.junit.jupiter.api.AfterEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class SpringTest {

	@AfterEach
	void tearDown() {
		// do common tidy up here, e.g. Clock.unfreeze();
	}
}

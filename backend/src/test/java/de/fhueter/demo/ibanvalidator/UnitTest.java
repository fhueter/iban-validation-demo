package de.fhueter.demo.ibanvalidator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class UnitTest {

	@AfterEach
	void tearDown() {
		// do common tidy up here, e.g. Clock.unfreeze();
	}
}

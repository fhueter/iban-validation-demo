package de.fhueter.demo.ibanvalidator.repository;

import de.fhueter.demo.ibanvalidator.model.Bank;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

	@Nullable Bank findByBankCode(String bankCode);
}

package ru.spbmtsb.bankbonus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spbmtsb.bankbonus.models.BankAccount;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    Optional<BankAccount> findByName(String name);
}

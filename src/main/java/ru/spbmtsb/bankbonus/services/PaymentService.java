package ru.spbmtsb.bankbonus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spbmtsb.bankbonus.models.BankAccount;
import ru.spbmtsb.bankbonus.payment.PaymentContext;
import ru.spbmtsb.bankbonus.repositories.BankAccountRepository;
import ru.spbmtsb.bankbonus.util.BankAccountNotFoundException;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PaymentService {
    private final static String ACCOUNT_NAME_A = "A";

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public PaymentService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Transactional
    public void doPayment(PaymentContext paymentContext) throws BankAccountNotFoundException {
        Optional<BankAccount> result = bankAccountRepository.findByName(ACCOUNT_NAME_A);
        result.orElseThrow(BankAccountNotFoundException::new);

        paymentContext.setBankAccount(result.get());
        paymentContext.doPayment();
    }

    public double getMoney() throws BankAccountNotFoundException {
        Optional<BankAccount> result = bankAccountRepository.findByName(ACCOUNT_NAME_A);
        result.orElseThrow(BankAccountNotFoundException::new);

        return result.get().getMoney();
    }

    public double getEMoney() throws BankAccountNotFoundException {
        Optional<BankAccount> result = bankAccountRepository.findByName(ACCOUNT_NAME_A);
        result.orElseThrow(BankAccountNotFoundException::new);

        return result.get().getEMoney();
    }
}

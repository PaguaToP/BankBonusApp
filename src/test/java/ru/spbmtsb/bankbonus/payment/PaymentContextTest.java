package ru.spbmtsb.bankbonus.payment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.spbmtsb.bankbonus.models.BankAccount;

public class PaymentContextTest {
    private BankAccount bankAccount;

    @BeforeEach
    void initialize() {
        bankAccount = new BankAccount();
        bankAccount.setMoney(5000);
        bankAccount.setEMoney(0);
    }

    @Test
    public void constructor_InvalidType_ThrowException() {
        Assertions.assertThrows(
                IllegalArgumentException.class, () -> new PaymentContext("qwerty", 0));
    }

    @Test
    public void constructor_NegativeAmount_ThrowException() {
        Assertions.assertThrows(
                IllegalArgumentException.class, () -> new PaymentContext("Online", -1));
    }

    @Test
    public void doPayment_Online100() {
        PaymentContext paymentContext = new PaymentContext("Online", 100);
        paymentContext.setBankAccount(bankAccount);

        paymentContext.doPayment();

        Assertions.assertEquals(bankAccount.getMoney(), 4900);
        Assertions.assertEquals(bankAccount.getEMoney(), 17);
    }

    @Test
    public void doPayment_Shop120() {
        PaymentContext paymentContext = new PaymentContext("Shop", 120);
        paymentContext.setBankAccount(bankAccount);

        paymentContext.doPayment();

        Assertions.assertEquals(bankAccount.getMoney(), 4880);
        Assertions.assertEquals(bankAccount.getEMoney(), 12);
    }

    @Test
    public void doPayment_Online301() {
        PaymentContext paymentContext = new PaymentContext("Online", 301);
        paymentContext.setBankAccount(bankAccount);

        paymentContext.doPayment();

        Assertions.assertEquals(bankAccount.getMoney(), 4699);
        Assertions.assertEquals(bankAccount.getEMoney(), 141.47);
    }

    @Test
    public void doPayment_Online17() {
        PaymentContext paymentContext = new PaymentContext("Online", 17);
        paymentContext.setBankAccount(bankAccount);

        paymentContext.doPayment();

        Assertions.assertEquals(bankAccount.getMoney(), 4981.3);
        Assertions.assertEquals(bankAccount.getEMoney(), 2.89);
    }

    @Test
    public void doPayment_Shop1000() {
        PaymentContext paymentContext = new PaymentContext("Shop", 1000);
        paymentContext.setBankAccount(bankAccount);

        paymentContext.doPayment();

        Assertions.assertEquals(bankAccount.getMoney(), 4000);
        Assertions.assertEquals(bankAccount.getEMoney(), 400);
    }

    @Test
    public void doPayment_Online21() {
        PaymentContext paymentContext = new PaymentContext("Online", 21);
        paymentContext.setBankAccount(bankAccount);

        paymentContext.doPayment();

        Assertions.assertEquals(bankAccount.getMoney(), 4979);
        Assertions.assertEquals(bankAccount.getEMoney(), 3.57);
    }

    @Test
    public void doPayment_Shop570() {
        PaymentContext paymentContext = new PaymentContext("Shop", 570);
        paymentContext.setBankAccount(bankAccount);

        paymentContext.doPayment();

        Assertions.assertEquals(bankAccount.getMoney(), 4430);
        Assertions.assertEquals(bankAccount.getEMoney(), 228);
    }

    @Test
    public void doPayment_Online700() {
        PaymentContext paymentContext = new PaymentContext("Online", 700);
        paymentContext.setBankAccount(bankAccount);

        paymentContext.doPayment();

        Assertions.assertEquals(bankAccount.getMoney(), 4300);
        Assertions.assertEquals(bankAccount.getEMoney(), 329);
    }
}

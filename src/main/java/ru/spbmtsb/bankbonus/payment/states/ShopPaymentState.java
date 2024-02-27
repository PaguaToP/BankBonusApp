package ru.spbmtsb.bankbonus.payment.states;

import ru.spbmtsb.bankbonus.models.BankAccount;
import ru.spbmtsb.bankbonus.payment.PaymentContext;
import ru.spbmtsb.bankbonus.util.DoubleRounder;

/**
 * ShopPaymentState - снимает amount cо счета платежа, начислят 10% бонусов.
 * Переходит в PostPaymentState.
 */
public class ShopPaymentState implements PaymentState {
    private final static double BONUS_PERCENT = 0.1;
    @Override
    public void doAction(PaymentContext paymentContext) {

        final double eMoney = paymentContext.getAmount() * BONUS_PERCENT;

        final BankAccount bankAccount = paymentContext.getBankAccount();
        bankAccount.setMoney(
                DoubleRounder.round(bankAccount.getMoney() - paymentContext.getAmount())
        );
        bankAccount.setEMoney(
                DoubleRounder.round(bankAccount.getEMoney() + eMoney)
        );

        paymentContext.setState(new PostPaymentState());
    }
}

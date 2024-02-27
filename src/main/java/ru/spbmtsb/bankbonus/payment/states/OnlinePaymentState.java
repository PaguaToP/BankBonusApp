package ru.spbmtsb.bankbonus.payment.states;

import ru.spbmtsb.bankbonus.models.BankAccount;
import ru.spbmtsb.bankbonus.payment.PaymentContext;
import ru.spbmtsb.bankbonus.util.DoubleRounder;

/**
 * OnlinePaymentState - снимает amount cо счета платежа, начислят 17% бонусов.
 * Переходит в PostPaymentState.
 */
public class OnlinePaymentState implements PaymentState {
    private final static double BONUS_PERCENT = 0.17;

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

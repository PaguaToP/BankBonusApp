package ru.spbmtsb.bankbonus.payment.states;

import ru.spbmtsb.bankbonus.models.BankAccount;
import ru.spbmtsb.bankbonus.payment.PaymentContext;
import ru.spbmtsb.bankbonus.util.DoubleRounder;

/**
 * AdditionalBonusState - начисляет дополнительно 30% бонусов на счет платежа.
 * Переходит в CompletedState.
 */
public class AdditionalBonusState implements PaymentState {
    private static final double BONUS_PERCENT = 0.3;

    @Override
    public void doAction(PaymentContext paymentContext) {

        final double eMoney = paymentContext.getAmount() * BONUS_PERCENT;
        final BankAccount bankAccount = paymentContext.getBankAccount();
        bankAccount.setEMoney(
                DoubleRounder.round(bankAccount.getEMoney() + eMoney)
        );

        paymentContext.setState(new CompletedState());
    }
}

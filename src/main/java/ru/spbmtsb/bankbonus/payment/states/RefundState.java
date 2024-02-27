package ru.spbmtsb.bankbonus.payment.states;

import ru.spbmtsb.bankbonus.payment.PaymentContext;
import ru.spbmtsb.bankbonus.util.DoubleRounder;

/**
 * RefundState - снимает дополнительно комиссию 10% от amount со счёта платежа.
 * Переходит в CompletedState.
 */
public class RefundState implements PaymentState {
    private static final double REFUND_PERCENT = 0.1;

    @Override
    public void doAction(PaymentContext paymentContext) {

        final double refundSum = paymentContext.getAmount() * REFUND_PERCENT;

        paymentContext.getBankAccount().setMoney(
                DoubleRounder.round(paymentContext.getBankAccount().getMoney() - refundSum)
        );

        paymentContext.setState(new CompletedState());
    }
}

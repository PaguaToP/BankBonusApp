package ru.spbmtsb.bankbonus.payment.states;

import ru.spbmtsb.bankbonus.payment.PaymentContext;

/**
 * PostPaymentState - переходит в одно из состояний:
 *     RefundState - если amount < 20;
 *     AdditionalBonusState - если amount > 300;
 *     CompletedState - в остальных случаях.
 */
public class PostPaymentState implements PaymentState {
    @Override
    public void doAction(PaymentContext paymentContext) {

        if (paymentContext.getAmount() < 20)
            paymentContext.setState(new RefundState());
        else if (paymentContext.getAmount() > 300)
            paymentContext.setState(new AdditionalBonusState());
        else
            paymentContext.setState(new CompletedState());
    }
}

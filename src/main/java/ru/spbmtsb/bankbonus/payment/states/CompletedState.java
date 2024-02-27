package ru.spbmtsb.bankbonus.payment.states;

import ru.spbmtsb.bankbonus.payment.PaymentContext;

/**
 * CompletedState - финальный стейт - переводит PaymentContext в isCompleted.
 * Завершает выполнение doPayment().
 */
public class CompletedState implements PaymentState {
    @Override
    public void doAction(PaymentContext paymentContext) {
        paymentContext.setCompleted(true);
    }
}

package ru.spbmtsb.bankbonus.payment.states;

import ru.spbmtsb.bankbonus.payment.PaymentContext;

/**
 * InitialState - начальный стейт, устанавливается при создании PaymentContext.
 * Переходит в ShopPaymentState или OnlinePaymentState в зависимости от типа платежа.
 */
public class InitialState implements PaymentState {
    @Override
    public void doAction(PaymentContext paymentContext) {

        switch (paymentContext.getType()) {
            case SHOP -> paymentContext.setState(new ShopPaymentState());
            case ONLINE -> paymentContext.setState(new OnlinePaymentState());

            default -> paymentContext.setState(new CompletedState());
        }
    }
}

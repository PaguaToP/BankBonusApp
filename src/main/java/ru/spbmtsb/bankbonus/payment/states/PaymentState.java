package ru.spbmtsb.bankbonus.payment.states;

import ru.spbmtsb.bankbonus.payment.PaymentContext;

/**
 * PaymentState - интерфейс стейтов
 */
public interface PaymentState {
    /**
     * Основной метод действия стейта
     * @param paymentContext для доступа к контексту
     */
    void doAction(PaymentContext paymentContext);
}

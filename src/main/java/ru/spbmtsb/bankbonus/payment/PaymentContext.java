package ru.spbmtsb.bankbonus.payment;

import lombok.Getter;
import lombok.Setter;
import ru.spbmtsb.bankbonus.models.BankAccount;
import ru.spbmtsb.bankbonus.payment.states.InitialState;
import ru.spbmtsb.bankbonus.payment.states.PaymentState;

/**
 * PaymentContext - содержит в себе всю необходимую информацию для платежа.
 * <p>Поля:
 *    <p>PaymentType type        - тип платежа
 *    <p>int amount              - сумма платежа
 *    <p>BankAccount bankAccount - банковский счет списания
 *    <p>PaymentState state      - состояние платежа
 *    <p>boolean isCompleted     - флаг завершенности платежа
 */
public class PaymentContext {
    @Getter
    private final PaymentType type;
    @Getter
    private final int amount;

    @Getter
    @Setter
    private BankAccount bankAccount;

    @Setter
    private PaymentState state = new InitialState();

    @Setter
    @Getter
    private boolean isCompleted = false;

    public PaymentContext(String type, int amount) throws IllegalArgumentException {
        this.type = PaymentType.buildPaymentType(type);

        if(amount < 0)
            throw new IllegalArgumentException("Операция не может быть отрицательной!");

        this.amount = amount;
    }

    /**
     * Главный метод проведения платежа
     */
    public void doPayment() {
        while (!isCompleted)
            state.doAction(this);
    }
}

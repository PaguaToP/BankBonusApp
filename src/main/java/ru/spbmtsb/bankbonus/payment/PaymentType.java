package ru.spbmtsb.bankbonus.payment;

/**
 * PaymentType - типы платежей
 */
public enum PaymentType {
    SHOP,
    ONLINE;

    static PaymentType buildPaymentType(String type) throws IllegalArgumentException {
        switch (type) {
            case "Shop": return SHOP;
            case "Online": return ONLINE;
        }

        throw new IllegalArgumentException("Неподдерживаемый тип платежа!");
    }
}

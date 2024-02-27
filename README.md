# BankBonusApp

## Запуск приложения
Необходимо создать БД в `Postgresql` и прописать данные для подключения в
[application.properties](src%2Fmain%2Fresources%2Fapplication.properties)

`Flyway` накатит начальную структуру таблиц при первом запуске приложения.


## Endpoinst:
1. `GET /api/payment/{Shop|Online}/{amount}` - производит платёж
2. `GET /api/bankAccountOfEMoney` - возвращает количество бонусов на счету
3. `GET /api/money` - возвращает количество денег на счету

   Порт по-умолчанию `8080`

### Обработка ошибок:
API вернет ошибки, в случае если:
1. Тип платежа отличный от `Shop|Online`
2. Отрицательный `amount`
3. Не удается найти счёт платежа

## Бизнес-логика:
Основная бизнес-логика содержится в пакете **payment**

**PaymentContext** - содержит в себе всю необходимую информацию для платежа.\
**PaymentType** - тип платежа \
**PaymentState** - интерфейс состояний платежа

### Граф состояний:
``` 
        InitialState
         /         \
ShopPaymentState OnlinePaymentState
         \         /
       PostPaymentState
        /     |    \
 RefundState  |   AdditionalBonusState
        \     |    /
       CompletedState
```

### Состояния платежа:

1. `InitialState` - начальное состояние, устанавливается при создании PaymentContext.
   * Переходит в ShopPaymentState или OnlinePaymentState в зависимости от типа платежа.

2. `ShopPaymentState` - снимает `amount` cо счета платежа, начислят `10%` бонусов.
   * Переходит в PostPaymentState.

3. `OnlineState` - снимает `amount` cо счета платежа, начислят `17%` бонусов.
   * Переходит в PostPaymentState.

4. `PostPaymentState` - переходит в одно из состояний:
   * RefundState - если `amount < 20`;
   * AdditionalBonusState - если `amount > 300`;
   * CompletedState - в остальных случаях.

5. `RefundState` - снимает дополнительно комиссию `10%` от amount со счёта платежа.
   * Переходит в CompletedState.

6. `AdditionalBonusState` - начисляет дополнительно `30%` бонусов на счет платежа.
   * Переходит в CompletedState.

7. `CompletedState` - финальный стейт - переводит PaymentContext в isCompleted.
   * Завершает выполнение платежа.


### Unit тесты:
`PaymentContextTest` содержит unit-тесты бизнес-логики.

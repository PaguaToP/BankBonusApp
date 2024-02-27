package ru.spbmtsb.bankbonus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spbmtsb.bankbonus.payment.PaymentContext;
import ru.spbmtsb.bankbonus.services.PaymentService;
import ru.spbmtsb.bankbonus.util.BankAccountNotFoundException;
import ru.spbmtsb.bankbonus.util.EMoneyResponse;
import ru.spbmtsb.bankbonus.util.MoneyResponse;
import ru.spbmtsb.bankbonus.util.PaymentResponse;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final PaymentService paymentService;

    @Autowired
    public ApiController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payment/{type}/{amount}")
    public ResponseEntity<PaymentResponse> paymentHandler(@PathVariable("type") String type,
                                                          @PathVariable("amount") int amount) {

        PaymentContext paymentContext;
        try {
            paymentContext = new PaymentContext(type, amount);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new PaymentResponse(e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }

        paymentService.doPayment(paymentContext);

        return new ResponseEntity<>(new PaymentResponse("OK"),
                HttpStatus.OK);
    }

    @GetMapping("/bankAccountOfEMoney")
    public ResponseEntity<EMoneyResponse> getEMoney() {
        return new ResponseEntity<>(new EMoneyResponse(paymentService.getEMoney()),
                HttpStatus.OK);
    }

    @GetMapping("/money")
    public ResponseEntity<MoneyResponse> getMoney() {
        return new ResponseEntity<>(new MoneyResponse(paymentService.getMoney()),
                HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<PaymentResponse> handler(BankAccountNotFoundException e) {
        return new ResponseEntity<>(new PaymentResponse("Банковский счёт не найден!"),
                HttpStatus.NOT_FOUND);
    }
}

package com.example.tbdemo.currency.controller;

import com.example.tbdemo.currency.service.CurrencyService;
import com.example.tbdemo.currency.model.Currency;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("api/currency")
    public List<Currency> currency() {
        return currencyService.getAllCurrencies();
    }

    @PostMapping("api/conversion")
    public double conversion(@RequestParam("from") String from,
                             @RequestParam("to") String to,
                             @RequestParam("amount") long amount) {
        // Check whether the Currency is in the DB
        if (currencyService.existByName(from) && currencyService.existByName(to)) {
            double exchangeRate = currencyService.findByName(from).getExchangeValue() / currencyService.findByName(to).getExchangeValue();
            return (amount / exchangeRate);
        }
        return 0; // Possibility to use Error msg that Currency is not in DB
    }
}

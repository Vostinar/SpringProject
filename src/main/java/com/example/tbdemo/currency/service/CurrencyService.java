package com.example.tbdemo.currency.service;

import com.example.tbdemo.currency.model.Currency;
import com.example.tbdemo.currency.repository.CurrencyRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public double calcConversion(String from, String to, double amount) {
        // Check whether the Currency is in the DB throw EX currency not found in DB
        if (!currencyRepository.existsByName(from)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ex currency " + from + " not found in DB");
        }
        if (!currencyRepository.existsByName(to)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ex currency " + to + " not found in DB");
        }
        double exchangeRate = currencyRepository.findByName(from).getExchangeValue() / currencyRepository.findByName(to).getExchangeValue();
        return (amount / exchangeRate);
    }
}

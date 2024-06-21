package com.example.tbdemo.currency.service;

import com.example.tbdemo.currency.model.Currency;
import com.example.tbdemo.currency.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Currency findByName(String name) {
        return currencyRepository.findByName(name);
    }

    public boolean existByName(String name) {
        return currencyRepository.existsByName(name);
    }
}

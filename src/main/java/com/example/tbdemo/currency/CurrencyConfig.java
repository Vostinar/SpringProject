package com.example.tbdemo.currency;

import com.example.tbdemo.currency.model.Currency;
import com.example.tbdemo.currency.repository.CurrencyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CurrencyConfig {

    @Bean
    CommandLineRunner commandLineRunner(CurrencyRepository currencyRepository) {
        return args -> {
            currencyRepository.saveAll(List.of(
                    new Currency("EUR", 1.0),
                    new Currency("USD", 1.0749),
                    new Currency("CZK", 24.91),
                    new Currency("AUD", 1.6125),
                    new Currency("JPY", 169.78)
            ));
        };
    }
}

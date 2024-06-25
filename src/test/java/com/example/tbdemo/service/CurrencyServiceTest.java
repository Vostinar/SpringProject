package com.example.tbdemo.service;

import com.example.tbdemo.currency.model.Currency;
import com.example.tbdemo.currency.repository.CurrencyRepository;
import com.example.tbdemo.currency.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;
    @InjectMocks
    private CurrencyService testCurrencyService;

    @BeforeEach
    void setUp() {
        testCurrencyService = new CurrencyService(currencyRepository);
    }

    @Test
    void testGetAllCurrencies() {
        System.out.println("Service testing testGetAllCurrencies from DB");
        // given
        Currency eurCurrency = new Currency("EUR", 1.0);
        when(currencyRepository.findAll()).thenReturn(Arrays.asList(eurCurrency));
        // when
        List<Currency> listOfCurrencies = testCurrencyService.getAllCurrencies();
        // then
        System.out.println("Verifying results");
        assertNotNull(listOfCurrencies, "List of listOfCurrencies mustn't be null");
        assertFalse(listOfCurrencies.isEmpty(), "List of listOfCurrencies mustn't be empty");
        assertEquals(1, listOfCurrencies.size(), "List of listOfCurrencies must contain exactly one element");
        verify(currencyRepository).findAll();
        System.out.println("Service testing testGetAllCurrencies from DB finished");
    }

    @Test
    void testCalcConversionCurrencyNotFound() {
        System.out.println("Service testing testCalcConversionCurrencyNotFound");
        // given
        when(currencyRepository.existsByName("USD")).thenReturn(false);
        // when
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            testCurrencyService.calcConversion("USD", "EUR", 100);
        });
        // then
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("404 NOT_FOUND \"Ex currency USD not found in DB\"", exception.getMessage());
        System.out.println("Service testing testCalcConversionCurrencyNotFound finished");
    }

    @Test
    void calcConversion() {
        System.out.println("Service testing calcConversion for JPY to USD");
        // given
        double amount = 100;
        Currency jpyCurrency = new Currency("JPY", 169.78);
        Currency usdCurrency = new Currency("USD", 1.0749);
        when(currencyRepository.existsByName(jpyCurrency.getName())).thenReturn(true);
        when(currencyRepository.existsByName(usdCurrency.getName())).thenReturn(true);
        when(currencyRepository.findByName(jpyCurrency.getName())).thenReturn(jpyCurrency);
        when(currencyRepository.findByName(usdCurrency.getName())).thenReturn(usdCurrency);
        // when
        double result = testCurrencyService.calcConversion(jpyCurrency.getName(), usdCurrency.getName(), amount);
        // then
        assertEquals(result, 0.6331134409235482, "Conversion result mismatch");
        System.out.println("Service testing findByName finished");
    }
}

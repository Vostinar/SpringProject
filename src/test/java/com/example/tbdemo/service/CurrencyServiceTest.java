package com.example.tbdemo.service;

import com.example.tbdemo.currency.model.Currency;
import com.example.tbdemo.currency.repository.CurrencyRepository;
import com.example.tbdemo.currency.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    void testFindByName() {
        System.out.println("Service testing findByName for JPY");
        // given
        String name = "JPY";
        Currency jpyCurrency = new Currency("JPY", 110.0);
        when(currencyRepository.findByName(name)).thenReturn(jpyCurrency);
        // when
        Currency currency = testCurrencyService.findByName(name);
        // then
        assertNotNull(currency);
        assertEquals(name, currency.getName(), "Name must match");
        assertEquals(110.0, currency.getExchangeValue(), "Currency exchange value must match");
        System.out.println("Service testing findByName finished");
    }

    @Test
    void testExistByName() {
        System.out.println("Service testing testExistByName for CZK");
        // given
        String name = "CZK";
        when(currencyRepository.existsByName(name)).thenReturn(true);
        // when
        boolean exists = testCurrencyService.existByName(name);
        // then
        assertTrue(exists, "Currency must exist");
        System.out.println("Service testing testExistByName finished");
    }
}

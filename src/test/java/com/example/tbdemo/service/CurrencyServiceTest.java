package com.example.tbdemo.service;

import com.example.tbdemo.currency.model.Currency;
import com.example.tbdemo.currency.repository.CurrencyRepository;
import com.example.tbdemo.currency.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;
    private CurrencyService testCurrencyService;

    @BeforeEach
    void setUp() {
        testCurrencyService = new CurrencyService(currencyRepository);
    }

    @Test
    void testGetAllCurrencies() {
        System.out.println("Service testing testGetAllCurrencies from DB");
        // given
        currencyRepository.save(new Currency("EUR", 1.0));
        // when
        List<Currency> listOfCurrencies = testCurrencyService.getAllCurrencies();
        // then
        System.out.println(listOfCurrencies.size());
//        Assert.notEmpty(listOfCurrencies,"List of listOfCurrencies mustn't be empty");
        verify(testCurrencyService).getAllCurrencies();
        System.out.println("Service testing testGetAllCurrencies from DB finished");
    }

//    @Test
//    @Disabled
//    void testFindByName() {
//        System.out.println("Service testing findByName for JPY");
//        // given
//        String name = "JPY";
//        // when
//        Currency currency = testCurrencyService.findByName(name);
//        // then
//        assertThat(currency).isNotNull();
//        System.out.println("Service testing findByName finished");
//    }
//
//    @Test
//    @Disabled
//    void testExistByName() {
//        System.out.println("Service testing testExistByName for CZK");
//        // given
//        String name = "CZK";
//        // when
//        boolean exists = testCurrencyService.ExistByName(name);
//        // then
//        assertThat(exists).isTrue();
//        System.out.println("Service testing testExistByName finished");
//    }
}

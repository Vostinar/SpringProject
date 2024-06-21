package com.example.tbdemo.repository;

import com.example.tbdemo.currency.model.Currency;
import com.example.tbdemo.currency.repository.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CurrencyRepositoryTest {

    @Autowired
    private CurrencyRepository testRepository;

    @BeforeEach
    void setUp() {
        testRepository.saveAll(List.of(
                new Currency("EUR", 1.0),
                new Currency("USD", 1.0749),
                new Currency("CZK", 24.91),
                new Currency("AUD", 1.6125),
                new Currency("JPY", 169.78)
        ));
    }

    @Test
    void testExistsByName() {
        System.out.println("Repo testing existsByName for Euro");
        // given
        String name = "EUR";
        testRepository.existsByName(name);
        // when
        boolean exists = testRepository.existsByName(name);
        // then
        assertThat(exists).isTrue();
        System.out.println("Repo testing existsByName finished");
    }

    @Test
    void testFindByName() {
        System.out.println("Repo testing findByName for USD");
        // given
        String name = "USD";
        testRepository.findByName(name);
        // when
        Currency exists = testRepository.findByName(name);
        // then
        assertThat(exists).isNotNull();
        System.out.println("Repo testing findByName finished");
    }
}

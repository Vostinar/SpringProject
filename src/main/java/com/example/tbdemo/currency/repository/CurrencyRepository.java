package com.example.tbdemo.currency.repository;

import com.example.tbdemo.currency.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    public Currency findByName(String name);

    Boolean existsByName(String name);
}

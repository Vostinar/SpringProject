//package com.example.tbdemo.integration;
//
//import com.example.tbdemo.currency.model.Currency;
//import com.example.tbdemo.currency.repository.CurrencyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ws.server.endpoint.annotation.Endpoint;
//import org.springframework.ws.server.endpoint.annotation.RequestPayload;
//import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
//
//import java.util.List;
//
//@Endpoint
//public class CurrencyEndPoint {
//    @Autowired
//    private CurrencyRepository currencyRepository;
//
//    @ResponsePayload
//    public double conversion(@RequestPayload String from,
//                             @RequestPayload String to,
//                             @RequestPayload double amount) {
//        currencyRepository.saveAll(List.of(
//                new Currency("USD", 1.0749),
//                new Currency("CZK", 24.91)
//        ));
//        double exchangeRate = currencyRepository.findByName(from).getExchangeValue() / currencyRepository.findByName(to).getExchangeValue();
//        return (amount / exchangeRate);
//    }
//}

//package com.example.tbdemo.integration;
//
//import com.example.tbdemo.currency.repository.CurrencyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.server.ResponseStatusException;
//import org.springframework.ws.server.endpoint.annotation.Endpoint;
//import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
//import org.springframework.ws.server.endpoint.annotation.RequestPayload;
//
//@Endpoint
//public class CurrencyEndPoint {
//    @Autowired
//    private CurrencyRepository currencyRepository;
//
////    @ResponsePayload
//    @PayloadRoot(namespace = "http://127.0.0.1:8126/api", localPart = "conversion")
//    public double conversion(@RequestPayload String from,
//                             @RequestPayload String to,
//                             @RequestPayload double amount) {
//        if (currencyRepository.existsByName(from) && currencyRepository.existsByName(to)) {
//            double exchangeRate = currencyRepository.findByName(from).getExchangeValue() / currencyRepository.findByName(to).getExchangeValue();
//            return (amount / exchangeRate);
//        }
//        return 0;
//    }
//}

package com.example.tbdemo.controller;

import com.example.tbdemo.currency.controller.CurrencyController;
import com.example.tbdemo.currency.model.Currency;
import com.example.tbdemo.currency.service.CurrencyService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CurrencyController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CurrencyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService currencyService;

    @Test
    public void testConversion() throws Exception {
        Currency from = new Currency("USD", 1.0749);
        Currency to = new Currency("CZK", 24.91);
        double amount = 100;
        given(currencyService.existByName(from.getName())).willReturn(true);
        given(currencyService.existByName(to.getName())).willReturn(true);
        given(currencyService.findByName(from.getName())).willReturn(from);
        given(currencyService.findByName(to.getName())).willReturn(to);

        double expectedConversionResult = amount / (from.getExchangeValue() / to.getExchangeValue());

        mockMvc.perform(post("/api/conversion")
                        .param("from", "USD")
                        .param("to", to.getName())
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expectedConversionResult)));
    }

    @Test
    public void testCurrency() throws Exception {
        List<Currency> listOfCurrencies = List.of(
                new Currency("EUR", 1.0),
                new Currency("USD", 1.0749),
                new Currency("CZK", 24.91),
                new Currency("AUD", 1.6125),
                new Currency("JPY", 169.78)
        );
        Gson gson = new Gson();
        String jsonArrayOfCurrencies = gson.toJson(listOfCurrencies);

        given(currencyService.getAllCurrencies()).willReturn(listOfCurrencies);

        mockMvc.perform(get("/api/currency")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonArrayOfCurrencies));
    }
}

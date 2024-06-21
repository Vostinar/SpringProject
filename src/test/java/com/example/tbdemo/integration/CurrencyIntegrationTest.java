//package com.example.tbdemo.integration;
//
//import com.example.tbdemo.currency.model.Currency;
//import com.example.tbdemo.currency.repository.CurrencyRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.webservices.server.AutoConfigureMockWebServiceClient;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.ws.test.server.MockWebServiceClient;
//import org.springframework.xml.transform.StringSource;
//
//import java.io.IOException;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.ws.test.server.RequestCreators.withPayload;
//import static org.springframework.ws.test.server.ResponseMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockWebServiceClient
//public class CurrencyIntegrationTest {
//
//    @Autowired
//    private MockWebServiceClient client;
//
//    @MockBean
//    private CurrencyRepository currencyRepository;
//
//    @Test
//    void givenXmlRequest_whenServiceInvoked_thenValidResponse() throws IOException {
//        Currency from = new Currency("USD", 1.0749);
//        Currency to = new Currency("CZK", 24.91);
//        when(currencyRepository.findByName("USD")).thenReturn(from);
//        when(currencyRepository.findByName("CZK")).thenReturn(to);
//
//        StringSource request = new StringSource(
//                "<api:CurrencyEndPoint xmlns:api='http://127.0.0.1:8080/api/conversion'>" +
//                            "<api:from>USD</api:from>" +
//                            "<api:to>CZK</api:to>" +
//                            "<api:amount>43.15134484142914</api:amount>" +
//                        "</api:CurrencyEndPoint>"
//        );
//
//        StringSource expectedResponse = new StringSource(
//                "<api:CurrencyEndPoint xmlns:api='http://127.0.0.1:8080/api/conversion'>" +
//                            "<api:result>1000.0</api:result>" +
//                        "</api:CurrencyEndPoint>"
//        );
//
//        client.sendRequest(withPayload(request))
//                .andExpect(noFault())
//                .andExpect(payload(expectedResponse))
//                .andExpect(xpath("/bd:getConversionResponse/bd:conversionResult").evaluatesTo("1000.0"));
//    }
//}

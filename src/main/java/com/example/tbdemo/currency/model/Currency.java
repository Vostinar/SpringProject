package com.example.tbdemo.currency.model;

import jakarta.persistence.*;
import lombok.*;

@Entity // Model
@Table(name = "currencies") // Table definition
@Data // Getters n setters
@NoArgsConstructor // Empty construct
@AllArgsConstructor // All arg construct
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "exchangeValue")
    private double exchangeValue;

    public Currency(String name, double exchangeValue) {
        this.name = name;
        this.exchangeValue = exchangeValue;
    }
}
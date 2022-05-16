package com.fourk.kursywalut4k.domain.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalculatorResult {
    private String baseCurrencyName;
    private String targetCurrencyName;
    private double amount;
    private double result;
}

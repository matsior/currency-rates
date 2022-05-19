package com.fourk.currencies4K.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalculateResult {
    private String baseCurrencyCode;
    private String baseCurrencyName;
    private String targetCurrencyCode;
    private String targetCurrencyName;
    private double amount;
    private double result;
}
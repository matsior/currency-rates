package com.fourk.kursywalut4k.domain.api;

import com.fourk.kursywalut4k.domain.calculator.Calculator;
import com.fourk.kursywalut4k.domain.datasource.CurrencyDataSource;

public class CalculatorService {
    private final Calculator calculator = new Calculator();
    private final CurrencyDataSource currencyDataSource = new CurrencyDataSource();

    public CalculatorResult calculate(String base, String target, double amount) {
        String baseCurrencyName = currencyDataSource.createCurrencyFromCode(base).getCurrency();
        double baseCurrencyMid = currencyDataSource.createCurrencyFromCode(base).getMid();
        String targetCurrencyName = currencyDataSource.createCurrencyFromCode(target).getCurrency();
        double targetCurrencyMid = currencyDataSource.createCurrencyFromCode(target).getMid();
        double result = calculator.calculate(baseCurrencyMid, targetCurrencyMid, amount);
        return new CalculatorResult(baseCurrencyName, targetCurrencyName, result, amount);
    }
}

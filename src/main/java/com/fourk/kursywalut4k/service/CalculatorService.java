package com.fourk.kursywalut4k.service;

import com.fourk.kursywalut4k.dto.CalculateRequest;
import com.fourk.kursywalut4k.dto.CalculateResult;
import com.fourk.kursywalut4k.calculator.Calculator;
import com.fourk.kursywalut4k.datasource.CurrencyDataSource;
import com.fourk.kursywalut4k.model.currency.CurrencyBasic;

public class CalculatorService {

    public CalculateResult calculate(CalculateRequest calculateRequest) {
        return new CalculatorMapper().map(calculateRequest);
    }

    private static class CalculatorMapper {
        private final Calculator calculator = new Calculator();
        private final CurrencyDataSource currencyDataSource = new CurrencyDataSource();

        CalculateResult map(CalculateRequest calculateRequest) {
            CurrencyBasic baseCurrency = currencyDataSource.createCurrencyFromCode(calculateRequest.getBase());
            CurrencyBasic targetCurrency = currencyDataSource.createCurrencyFromCode(calculateRequest.getTarget());
            double result = calculator.calculate(baseCurrency.getMid(), targetCurrency.getMid(), calculateRequest.getAmount());
            return new CalculateResult(
                    baseCurrency.getCode(),
                    baseCurrency.getCurrency(),
                    targetCurrency.getCode(),
                    targetCurrency.getCurrency(),
                    calculateRequest.getAmount(),
                    result
            );
        }
    }
}

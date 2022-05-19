package com.fourk.currencies4K.service;

import com.fourk.currencies4K.dto.CalculateRequest;
import com.fourk.currencies4K.dto.CalculateResult;
import com.fourk.currencies4K.utils.Calculator;
import com.fourk.currencies4K.utils.CurrencyDataSource;
import com.fourk.currencies4K.model.currency.CurrencyBasic;

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

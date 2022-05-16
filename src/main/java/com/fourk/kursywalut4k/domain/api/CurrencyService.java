package com.fourk.kursywalut4k.domain.api;

import com.fourk.kursywalut4k.domain.currency.BasicCurrency;
import com.fourk.kursywalut4k.domain.currency.CurrencyDao;
import com.fourk.kursywalut4k.domain.currency.ExtendedCurrency;
import com.fourk.kursywalut4k.domain.datasource.CurrencyDataSource;

import java.util.List;
import java.util.stream.Collectors;

public class CurrencyService {

    CurrencyDataSource currencyDataSource = new CurrencyDataSource();
    CurrencyDao currencyDao = new CurrencyDao();
    CurrencyMapper currencyMapper = new CurrencyMapper();

    public List<BasicCurrencyDto> getUserCurrencies(int userId) {
        return currencyDao.findUserSavedCurrenciesByUserId(userId)
                .stream()
                .map(currencyMapper::map)
                .collect(Collectors.toList());
    }

    public List<BasicCurrencyDto> getBasicCurrencies() {
        return currencyDataSource.createBasicCurrencyList()
                .stream()
                .map(currencyMapper::map)
                .collect(Collectors.toList());
    }

    public List<ExtendedCurrencyDto> getExtendedCurrencies() {
        return currencyDataSource.createExtendedCurrencyList()
                .stream()
                .map(currencyMapper::map)
                .collect(Collectors.toList());
    }

    public BasicCurrencyDto getArchivalCurrency(String code, String date) {
        BasicCurrency basicCurrency = currencyDataSource.createCurrencyFromCode(code, date);
        return currencyMapper.map(basicCurrency);
    }

    private static class CurrencyMapper {

        BasicCurrencyDto map(BasicCurrency basicCurrency) {
            return new BasicCurrencyDto(
                    basicCurrency.getCurrency(),
                    basicCurrency.getCode(),
                    basicCurrency.getMid()
            );
        }

        ExtendedCurrencyDto map(ExtendedCurrency extendedCurrency) {
            return new ExtendedCurrencyDto(
                    extendedCurrency.getCurrency(),
                    extendedCurrency.getCode(),
                    extendedCurrency.getBid(),
                    extendedCurrency.getAsk()
            );
        }
    }
}

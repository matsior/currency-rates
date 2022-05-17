package com.fourk.kursywalut4k.service;

import com.fourk.kursywalut4k.dao.CurrencyDao;
import com.fourk.kursywalut4k.datasource.CurrencyDataSource;
import com.fourk.kursywalut4k.dto.BasicCurrencyDto;
import com.fourk.kursywalut4k.dto.ExtendedCurrencyDto;
import com.fourk.kursywalut4k.model.currency.CurrencyBasic;
import com.fourk.kursywalut4k.model.currency.CurrencyExtended;

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
        CurrencyBasic currencyBasic = currencyDataSource.createCurrencyFromCode(code, date);
        return currencyMapper.map(currencyBasic);
    }

    private static class CurrencyMapper {

        BasicCurrencyDto map(CurrencyBasic currencyBasic) {
            return new BasicCurrencyDto(
                    currencyBasic.getCurrency(),
                    currencyBasic.getCode(),
                    currencyBasic.getMid()
            );
        }

        ExtendedCurrencyDto map(CurrencyExtended currencyExtended) {
            return new ExtendedCurrencyDto(
                    currencyExtended.getCurrency(),
                    currencyExtended.getCode(),
                    currencyExtended.getBid(),
                    currencyExtended.getAsk()
            );
        }
    }
}

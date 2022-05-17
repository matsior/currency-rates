package com.fourk.kursywalut4k.model.currency;

import lombok.Getter;

@Getter
public class CurrencyExtended extends Currency{
    private Double bid;
    private Double ask;

    public CurrencyExtended(String currency, String code, Double bid, Double ask) {
        super(currency, code);
        this.bid = bid;
        this.ask = ask;
    }
}

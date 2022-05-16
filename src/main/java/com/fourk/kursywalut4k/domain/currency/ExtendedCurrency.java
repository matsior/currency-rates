package com.fourk.kursywalut4k.domain.currency;

import lombok.Getter;

@Getter
public class ExtendedCurrency extends Currency{
    private Double bid;
    private Double ask;

    public ExtendedCurrency(String currency, String code, Double bid, Double ask) {
        super(currency, code);
        this.bid = bid;
        this.ask = ask;
    }
}

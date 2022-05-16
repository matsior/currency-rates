package com.fourk.kursywalut4k.domain.currency;

import lombok.Getter;

@Getter
public class BasicCurrency extends Currency{
    private Double mid;

    public BasicCurrency(String currency, String code, Double mid) {
        super(currency, code);
        this.mid = mid;
    }

    public Double getMid() {
        return mid;
    }
}

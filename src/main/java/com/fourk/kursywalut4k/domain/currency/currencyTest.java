package com.fourk.kursywalut4k.domain.currency;

public class currencyTest {

    public static void main(String[] args) {
        BasicCurrency currency = new BasicCurrency("złoty", "PLN", 1.0);
        System.out.println(currency.getCurrency());
    }
}

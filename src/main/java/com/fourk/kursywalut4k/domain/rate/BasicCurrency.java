package com.fourk.kursywalut4k.domain.rate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BasicCurrency {
    private String currency;
    private String code;
    private Double mid;
}

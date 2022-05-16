package com.fourk.kursywalut4k.domain.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BasicCurrencyDto {
    private String currency;
    private String code;
    private Double mid;
}
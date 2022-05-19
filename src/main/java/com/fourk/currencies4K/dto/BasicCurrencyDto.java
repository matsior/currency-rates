package com.fourk.currencies4K.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BasicCurrencyDto {
    private String currency;
    private String code;
    private Double mid;
}
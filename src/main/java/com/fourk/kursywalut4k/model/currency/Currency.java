package com.fourk.kursywalut4k.model.currency;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Currency {
    private String currency;
    private String code;
}

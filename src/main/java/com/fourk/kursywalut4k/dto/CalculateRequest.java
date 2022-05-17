package com.fourk.kursywalut4k.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalculateRequest {
    private String base;
    private String target;
    private double amount;
}

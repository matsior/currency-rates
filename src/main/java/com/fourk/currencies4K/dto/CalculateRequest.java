package com.fourk.currencies4K.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalculateRequest {
    private String base;
    private String target;
    private double amount;
}

package com.fourk.currencies4K.utils;

public class Calculator {

    public static double roundAvoid(double calculate, int places) {
        double scale = Math.pow(10, places);
        return Math.round(calculate * scale) / scale;
    }

    public double calculate(double base, double target, double amount) {
        return roundAvoid(base * amount / target, 3);
    }
}
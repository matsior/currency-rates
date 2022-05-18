package com.fourk.kursywalut4k.utils;

public abstract class PasswordValidation {
    public static boolean isPasswordValid(String password) {
        if (password.length() < 8) return false;
        if (!password.matches(".*[0-9].*")) return false;
        if (!password.matches(".*[a-z].*")) return false;
        if (!password.matches(".*[A-Z].*")) return false;
        if (!password.matches(".*[!@#$%&?].*")) return false;
        if (password.matches(".*[\\s].*")) return false;
        return true;
    }
}


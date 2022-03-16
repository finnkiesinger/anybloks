package com.blokusgang.anybloksclient.utils;

public class Validator {
    public static boolean validPassword(String password) {
        if (!containsUppercase(password)) {
            return false;
        }
        if (!containsLowercase(password)) {
            return false;
        }
        if (!containsDigit(password)) {
            return false;
        }

        return password.length() >= 6;
    }

    public static boolean containsUppercase(String sequence) {
        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);
            if (Character.isUpperCase(c)) {
                return true;
            }
        }

        return false;
    }

    public static boolean containsLowercase(String sequence) {
        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);
            if (Character.isLowerCase(c)) {
                return true;
            }
        }

        return false;
    }

    public static boolean containsDigit(String sequence) {
        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);
            if (Character.isDigit(c)) {
                return true;
            }
        }

        return false;
    }
}

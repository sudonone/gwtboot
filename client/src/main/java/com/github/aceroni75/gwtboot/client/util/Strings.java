package com.github.aceroni75.gwtboot.client.util;

public class Strings {

    public static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    public static Long longOrNull(String s) {
        try {
            return Long.parseLong(s);
        } catch (Throwable ignored) {
            return null;
        }
    }
}


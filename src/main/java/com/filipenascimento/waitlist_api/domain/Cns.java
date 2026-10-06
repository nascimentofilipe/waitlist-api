package com.filipenascimento.waitlist_api.domain;

public record Cns(String value) {

    private static final int VISIBLE_DIGITS = 4;
    private static final int CNS_LENGTH = 15;
    private static final String FIFTEEN_DIGITS_PATTERN = "\\d{15}";
    private static final String VALID_FIRST_DIGIT_PATTERN = "[12789]\\d{14}";

    public Cns {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("CNS is required");
        }

        value = value.replaceAll("\\D", "");

        if (!value.matches(FIFTEEN_DIGITS_PATTERN)) {
            throw new IllegalArgumentException("CNS must have 15 digits");
        }

        if (!value.matches(VALID_FIRST_DIGIT_PATTERN)) {
            throw new IllegalArgumentException("CNS must start with 1, 2, 7, 8 or 9");
        }

        if (!hasValidCheckDigit(value)) {
            throw new IllegalArgumentException("Invalid CNS: check digit mismatch");
        }
    }

    private static boolean hasValidCheckDigit(String digits) {
        int sum = 0;

        for (int i = 0; i < CNS_LENGTH; i++) {
            int digit = Character.getNumericValue(digits.charAt(i));
            int weight = CNS_LENGTH - i;
            sum += digit * weight;
        }

        return sum % 11 == 0;
    }

    @Override
    public String toString() {
        int hiddenDigits = CNS_LENGTH - VISIBLE_DIGITS;
        return "Cns[" + "*".repeat(hiddenDigits) + value.substring(hiddenDigits) + "]";
    }
}

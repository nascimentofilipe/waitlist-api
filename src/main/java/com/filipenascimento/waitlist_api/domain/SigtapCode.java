package com.filipenascimento.waitlist_api.domain;

public record SigtapCode(String value) {

    // After normalization, the value has only digits: it must have exactly 10.
    private static final String TEN_DIGITS_PATTERN = "\\d{10}";
    // Group 04 = surgical procedures.
    private static final String SURGICAL_GROUP_PATTERN = "04\\d{8}";

    public SigtapCode {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("SIGTAP code is required");
        }

        value = value.replaceAll("\\D", "");

        if (!value.matches(TEN_DIGITS_PATTERN)) {
            throw new IllegalArgumentException("SIGTAP code must have 10 digits");
        }

        if (!value.matches(SURGICAL_GROUP_PATTERN)) {
            throw new IllegalArgumentException("SIGTAP code must belong to group 04 (surgical procedures)");
        }
    }
}

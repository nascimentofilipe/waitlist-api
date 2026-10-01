package com.filipenascimento.waitlist_api.domain;

public record SigtapCode(String value) {
    private static final String SIGTAPCODE_PATTERN = "^04";

    public SigtapCode {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("SigtapCode is required");
        }

        value = value.replaceAll("\\D", "");

        if (value.length() != 10) {
            throw new IllegalArgumentException("SigtapCode value length should be 10 characters");
        }

        if (!value.matches(SIGTAPCODE_PATTERN)) {
            throw new IllegalArgumentException("SigtapCode value does not match expected pattern " + SIGTAPCODE_PATTERN);
        }
    }
}

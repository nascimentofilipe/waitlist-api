package com.filipenascimento.waitlist_api.domain;

public record SigtapCode(String value) {
    private static final String SIGTAPCODE_PATTERN = "^04\\d{8}";

    public SigtapCode {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("SigtapCode is required");
        }

        value = value.replaceAll("\\D", "");

        if (!value.matches(SIGTAPCODE_PATTERN)) {
            throw new IllegalArgumentException("SIGTAP code must belong to group 04 (surgical procedures)");
        }
    }
}

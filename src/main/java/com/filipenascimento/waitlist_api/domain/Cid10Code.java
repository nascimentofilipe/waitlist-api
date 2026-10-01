package com.filipenascimento.waitlist_api.domain;

public record Cid10Code(String value) {
    private static final String CID10_PATTERN = "[A-Z]\\d{2}(\\.\\d)?";

    public Cid10Code {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("CID-10 code is required");
        }

        value = value.strip().toUpperCase();

        if (!value.matches(CID10_PATTERN)) {
                throw new IllegalArgumentException("Invalid CID-10 code format");
        }
    }
}

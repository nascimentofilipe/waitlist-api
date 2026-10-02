package com.filipenascimento.waitlist_api.domain;

public record Cns(String value) {

    public Cns {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("CNS is required");
        }

        value = value.replaceAll("\\D", "");
    }
}

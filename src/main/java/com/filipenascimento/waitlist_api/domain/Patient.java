package com.filipenascimento.waitlist_api.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Patient {
    private final UUID id;
    private final Cns cns;
    private final String fullName;
    private final LocalDate birthDate;
    private final String phone;

    public Patient(UUID id, Cns cns, String fullName, LocalDate birthDate, String phone) {

        if (id == null) {
            throw new IllegalArgumentException("Patient ID is required");
        }

        if (cns == null) {
            throw new IllegalArgumentException("Patient CNS is required");
        }

        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Patient full name is required");
        }

        this.id = id;
        this.cns = cns;
        this.fullName = fullName.strip();
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public UUID getId() {
        return id;
    }

    public Cns getCns() {
        return cns;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Patient other)) return false;
        return id.equals(other.id);
    }


    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

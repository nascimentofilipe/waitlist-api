package com.filipenascimento.waitlist_api.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class PatientTest {

    private static final Cns VALID_CNS = new Cns("709960308246284");
    private static final LocalDate BIRTH_DATE = LocalDate.of(1985, 3, 12);

    @Test
    void shouldBeEqualWhenIdsAreEqual() {
        UUID id = UUID.randomUUID();
        Patient original = new Patient(id, VALID_CNS, "Maria Silva", BIRTH_DATE, "83900000001");
        Patient updated = new Patient(id, VALID_CNS, "Maria Silva Lima", BIRTH_DATE, "83900000002");

        assertThat(updated).isEqualTo(original);
        assertThat(updated).hasSameHashCodeAs(original);
    }

    @Test
    void shouldNotBeEqualWhenIdsAreDifferent() {
        UUID id = UUID.randomUUID();
        UUID otherId = UUID.randomUUID();

        Patient original = new Patient(id, VALID_CNS, "Maria Silva", BIRTH_DATE, "83900000001");
        Patient updated = new Patient(otherId, VALID_CNS, "Maria Silva Lima", BIRTH_DATE, "83900000002");

        assertThat(updated).isNotEqualTo(original);
    }


    @Test
    void shouldRejectMissingCns() {
        UUID id = UUID.randomUUID();

        assertThatThrownBy(() -> new Patient(id, null, "Maria Silva", BIRTH_DATE, "83900000001"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Patient CNS is required");
    }

    @Test
    void shouldRejectMissingId() {
        assertThatThrownBy(() -> new Patient(null, VALID_CNS, "Maria Silva", BIRTH_DATE, "83900000002"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Patient ID is required");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t"})
    void shouldRejectMissingFullName(String fullName) {
        UUID id = UUID.randomUUID();

        assertThatThrownBy(() -> new Patient(id, VALID_CNS, fullName, BIRTH_DATE, "83900000001"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Patient full name is required");
    }

    @Test
    void shoudlStripFullName() {
        Patient patient = new Patient(UUID.randomUUID(), VALID_CNS, "  Maria Silva Lima  ", BIRTH_DATE, "83900000001");
        assertThat(patient.getFullName()).isEqualTo("Maria Silva Lima");
    }
}

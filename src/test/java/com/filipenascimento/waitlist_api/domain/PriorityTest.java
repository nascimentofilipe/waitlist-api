package com.filipenascimento.waitlist_api.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.*;

public class PriorityTest {

    @Test
    void shouldHaveCoefficientOfOneForLowestPriority() {
        assertThat(Priority.D.getCoefficient()).isCloseTo(1.0, within(0.001));
    }

    @Test
    void shouldCalculateCoefficientFromMaxWaitDays() {
        assertThat(Priority.B.getCoefficient()).isCloseTo(6.0, within(0.001));
    }

    @Test
    void shouldBypassScoringForA1() {
        assertThat(Priority.A1.bypassesScoring()).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = Priority.class, names = {"A1"}, mode = EnumSource.Mode.EXCLUDE)
    void shouldNotBypassScoringForOtherPriorities(Priority priority) {
        assertThat(priority.bypassesScoring()).isFalse();
    }
}

package com.filipenascimento.waitlist_api.domain;

import org.apache.coyote.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.*;

public class RequestStatusTest {


    @ParameterizedTest
    @EnumSource(value = RequestStatus.class, names = {"PERFORMED", "CANCELED"})
    void shouldBeFinalOnlyForPerformedAndCanceled(RequestStatus requestStatus) {
        assertThat(requestStatus.isFinal()).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = RequestStatus.class, names = {"PERFORMED", "CANCELED"}, mode = EnumSource.Mode.EXCLUDE)
    void shouldNotBeFinalStatuses(RequestStatus status) {
        assertThat(status.isFinal()).isFalse();
    }

    @ParameterizedTest
    @CsvSource({
            "REQUESTED, IN_TRIAGE, true",
            "REQUESTED, SCHEDULED, false",
            "SCHEDULED, CANCELED, true",
            "PERFORMED, CANCELED, false",
            "CANCELED, CANCELED, false",
            "SCHEDULED, SCHEDULED, false"
    })
    void shouldFollowTransitionRules(RequestStatus from, RequestStatus to, boolean expected) {
        assertThat(from.canTransitionTo(to)).isEqualTo(expected);
    }

    @ParameterizedTest
    @EnumSource(value = RequestStatus.class, names = {"PERFORMED", "CANCELED"})
    void shouldNotTransitionFromFinalStatus(RequestStatus finalStatus) {
        for(RequestStatus target : RequestStatus.values()) {
            assertThat(finalStatus.canTransitionTo(target)).isFalse();
        }
    }
}

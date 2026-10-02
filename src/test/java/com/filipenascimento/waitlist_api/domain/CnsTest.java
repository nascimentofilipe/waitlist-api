package com.filipenascimento.waitlist_api.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
public class CnsTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"    "})
    void shouldRejectMissingCns(String input){
        assertThatThrownBy(()-> new Cns(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("CNS is required");
    }

    @Test
    void shouldNormalizeCns() {
        Cns cns = new Cns("709 9603 0824 6284");
        assertThat(cns.value()).isEqualTo("709960308246284");
    }
}

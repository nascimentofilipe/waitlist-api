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
    void shouldRejectMissingCns(String input) {
        assertThatThrownBy(() -> new Cns(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("CNS is required");
    }

    @Test
    void shouldNormalizeCns() {
        Cns cns = new Cns("709 9603 0824 6284");
        assertThat(cns.value()).isEqualTo("709960308246284");
    }

    @ParameterizedTest
    @ValueSource(strings = {"70996030824628", "7099603082462840"})
    void shouldRejectCnsWithWrongLength(String input) {
        assertThatThrownBy(() -> new Cns(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("CNS must have 15 digits");
    }

    @ParameterizedTest
    @ValueSource(strings = {"332319487574918", "618625276018958"})
    void shouldRejectCnsWithInvalidFirstDigit(String input) {
        assertThatThrownBy(() -> new Cns(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("CNS must start with 1, 2, 7, 8 or 9");
    }

    @Test
    void shouldRejectCnsWithInvalidCheckDigit() {
        assertThatThrownBy(() -> new Cns("709960308246285"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid CNS: check digit mismatch");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "152601815908304",
            "216613186091393",
            "709960308246284",
            "819482199351813",
            "990937865797545"
    })
    void shouldAcceptValidCns(String value) {
        assertThatNoException().isThrownBy(() -> new Cns(value));
    }

    @Test
    void shouldMaskCnsInToString() {
        String text = new Cns("709960308246284").toString();

        assertThat(text)
                .isEqualTo("Cns[***********6284]")
                .doesNotContain("709960308246284");
    }
}

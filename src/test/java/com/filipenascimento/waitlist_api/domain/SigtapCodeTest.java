package com.filipenascimento.waitlist_api.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class SigtapCodeTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"      "})
    void souldRejectMissingSigtapCode(String value) {
        assertThatThrownBy(() -> new SigtapCode(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("SIGTAP code is required");
    }

    @Test
    void shouldNormalizeCode() {
        SigtapCode sigtapCode = new SigtapCode("04.04.01.003-  4");
        assertThat(sigtapCode.value()).isEqualTo("0404010034");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0407030034", "04.07.03.003-4", "  0407030034  "})
    void shouldAcceptValidFormats(String value) {
        assertThatNoException().isThrownBy(() -> new SigtapCode(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0201010010", "1920101001"})
    void shouldRejectCodeOutsideSurgicalGroup(String value) {
        assertThatThrownBy(() -> new SigtapCode(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("SIGTAP code must belong to group 04 (surgical procedures)");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0407", "040703003412"})
    void shouldRejectCodeWithWrongLength(String value) {
    assertThatThrownBy(() -> new SigtapCode(value))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("SIGTAP code must have 10 digits");
    }

}

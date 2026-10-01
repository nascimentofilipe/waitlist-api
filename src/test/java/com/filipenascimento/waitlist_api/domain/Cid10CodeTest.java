package com.filipenascimento.waitlist_api.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class Cid10CodeTest {

    @ParameterizedTest
    @ValueSource(strings = {"K80", "K80.2", "k80"})
    void shouldAcceptValidFormats(String input) {
        assertThatNoException().isThrownBy(() -> {
            new Cid10Code(input);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"K8", "K80.", "K80.25", "80K"})
    void shouldRejectInvalidFormats(String input) {
        assertThatThrownBy(() -> new Cid10Code(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid CID-10 code format");
    }


    @Test
    void shouldNormalizeWhitespaceAndCase() {
        Cid10Code cid10Code = new Cid10Code(" k80.2 ");
        assertThat(cid10Code.value()).isEqualTo("K80.2");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"     "})
    void shouldRejectMissingCID10Code(String input) {
        assertThatThrownBy(() -> new Cid10Code(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("CID-10 code is required");
    }
}

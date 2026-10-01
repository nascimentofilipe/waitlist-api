package com.filipenascimento.waitlist_api;

import com.filipenascimento.waitlist_api.domain.Cid10Code;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class Cid10CodeTest {


    //            "K8"	false (um dígito só)
//            "K80."	false (ponto sem dígito)
//            "K80.25"	false (dois dígitos depois do ponto)
//            "80K"	false
    @ParameterizedTest
    @ValueSource(strings = {"K80", "K80.2"})
    void shouldAcceptValidFormats(String input) {
        assertThatNoException().isThrownBy(() -> {
            new Cid10Code(input);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"k80", "K8", "K80.", "K80.25", "80K"})
    void shouldRejectInvalidFormats(String input) {
        assertThatNoException().isThrownBy(() -> {
            new Cid10Code(input);
        });
    }


    @Test
    void shouldRemoveWhiteSpaces() {
        Cid10Code cid10Code = new Cid10Code(" k80.2 ");
        assertThat(cid10Code.toString().strip().toUpperCase()).isEqualTo("K80.2");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testIsNullOrEmpty(String input) {
        assertTrue(input == null || input.isEmpty());
    }
}

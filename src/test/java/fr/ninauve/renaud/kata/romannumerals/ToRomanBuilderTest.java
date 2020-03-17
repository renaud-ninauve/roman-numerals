package fr.ninauve.renaud.kata.romannumerals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class ToRomanBuilderTest {

    private ToRomanBuilder toRomanBuilder;

    @BeforeEach
    public void setUp() {
        this.toRomanBuilder = new ToRomanBuilder();
    }

    @Test
    public void should_return_empty_string_converter_when_no_converters() {
        final Function<Integer, String> actual = toRomanBuilder.build();
        assertEquals(actual.apply(10), "");
    }

    @Test
    public void should_return_empty_string_converter_when_none_applies() {
        final Function<Integer, String> actual = toRomanBuilder
                .partialToRomanConverter(n -> n > 10, n -> "toto")
                .build();
        assertEquals(actual.apply(3), "");
    }

    @Test
    public void should_return_composed_converters() {
        final Function<Integer, String> actual = toRomanBuilder
                .partialToRomanConverter(n -> n == 2, n -> "CONV-2")
                .partialToRomanConverter(n -> n == 0, n -> "CONV-0")
                .partialToRomanConverter(n -> n == 1, n -> "CONV-1")
                .build();

        assertEquals(actual.apply(1), "CONV-1");
        assertEquals(actual.apply(0), "CONV-0");
        assertEquals(actual.apply(2), "CONV-2");
    }
}
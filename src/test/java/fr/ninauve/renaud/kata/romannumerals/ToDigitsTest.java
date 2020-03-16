package fr.ninauve.renaud.kata.romannumerals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDigitsTest {

    public static Stream<Arguments> parameters() {

        return Stream.of(
                Arguments.of(1, singletonList(1)),
                Arguments.of(12, asList(2, 1)),
                Arguments.of(123, asList(3, 2, 1)),
                Arguments.of(1234, asList(4, 3, 2, 1))
        );
    }

    @ParameterizedTest(name = "{0} -> {1}.")
    @MethodSource("parameters")
    public void split_digits(int number, List<Integer> expected) {

        final List<Integer> actual = ToDigits.toDigits(number);
        assertEquals(expected, actual);
    }
}
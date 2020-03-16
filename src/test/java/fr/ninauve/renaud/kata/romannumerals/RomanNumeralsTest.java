package fr.ninauve.renaud.kata.romannumerals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class RomanNumeralsTest {

    @DisplayName("Valid numbers are in the range 1 to 3000.")
    @ParameterizedTest(name = "number {0} is valid.")
    @ValueSource(ints = {1, 500, 3000})
    public void valid_number(int number) {
        RomanNumerals.numberToRoman(number);
    }

    @DisplayName("Valid numbers are in the range 1 to 3000.")
    @ParameterizedTest(name = "number {0} is invalid.")
    @ValueSource(ints = {-1, 0, 3001})
    public void invalid_number(int number) {
        assertThrows(IllegalArgumentException.class,
                () -> RomanNumerals.numberToRoman(number));
    }

    @DisplayName("Bellow 3, the roman number is several I.")
    @ParameterizedTest(name = "{0} -> {1}.")
    @CsvSource({"1, I", "2, II", "3, III"})
    public void valid_number(int number, String expected) {
        final String actual = RomanNumerals.numberToRoman(number);
        assertEquals(expected, actual);
    }
}
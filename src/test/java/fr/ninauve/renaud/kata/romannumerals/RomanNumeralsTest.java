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
    public void bellow_3(int number, String expected) {
        final String actual = RomanNumerals.numberToRoman(number);
        assertEquals(expected, actual);
    }

    @DisplayName("4, 5, 9")
    @ParameterizedTest(name = "{0} -> {1}.")
    @CsvSource({"4, IV", "5, V", "9, IX"})
    public void equals_to_4_or_5_or_9(int number, String expected) {
        final String actual = RomanNumerals.numberToRoman(number);
        assertEquals(expected, actual);
    }

    @DisplayName("Between 6 and 8, there is a V followed by I.")
    @ParameterizedTest(name = "{0} -> {1}.")
    @CsvSource({"6, VI", "7, VII", "8, VIII"})
    public void between_6_and_8(int number, String expected) {
        final String actual = RomanNumerals.numberToRoman(number);
        assertEquals(expected, actual);
    }

    @DisplayName("Between 10 and 19, there is a X followed by the conversion of the last digit.")
    @ParameterizedTest(name = "{0} -> {1}.")
    @CsvSource({"10, X", "11, XI", "12, XII", "13, XIII", "14, XIV", "15, XV", "16, XVI", "17, XVII", "18, XVIII", "19, XIX"})
    public void between_10_and_19(int number, String expected) {
        final String actual = RomanNumerals.numberToRoman(number);
        assertEquals(expected, actual);
    }

    @DisplayName("Between 20 and 99.")
    @ParameterizedTest(name = "{0} -> {1}.")
    @CsvSource({"22, XXII", "24, XXIV", "42, XLII", "99, XCIX"})
    public void between_20_and_99(int number, String expected) {
        final String actual = RomanNumerals.numberToRoman(number);
        assertEquals(expected, actual);
    }

    @DisplayName("Above 100.")
    @ParameterizedTest(name = "{0} -> {1}.")
    @CsvSource({"100, C", "356, CCCLVI", "777, DCCLXXVII", "999, CMXCIX", "2020, MMXX"})
    public void above_100(int number, String expected) {
        final String actual = RomanNumerals.numberToRoman(number);
        assertEquals(expected, actual);
    }
}
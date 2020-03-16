package fr.ninauve.renaud.kata.romannumerals;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class RomanNumerals {

    public static String numberToRoman(final int number) {

        if (number < 1 || number > 3000) {
            throw new IllegalArgumentException();
        }
        if (number <= 3) {
            return IntStream.rangeClosed(1, number)
                    .mapToObj(n -> "I")
                    .collect(Collectors.joining());
        }
        if (number == 4) {
            return "IV";
        }
        if (number == 5) {
            return "V";
        }
        return null;
    }
}

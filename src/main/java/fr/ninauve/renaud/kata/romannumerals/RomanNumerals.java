package fr.ninauve.renaud.kata.romannumerals;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static fr.ninauve.renaud.kata.romannumerals.ToDigits.toDigits;

public final class RomanNumerals {

    private static final Function<Integer, Optional<String>> bellow10Converter = new PartialToRomanConverterComposite.Builder()
            .partialToRomanConverter(
                    n -> n <= 3,
                    RomanNumerals::repeatI)
            .partialToRomanConverter(
                    n -> n == 4,
                    n -> "IV")
            .partialToRomanConverter(
                    n -> n == 5,
                    n -> "V")
            .partialToRomanConverter(
                    n -> n >= 6 && n <= 8,
                    n -> "V" + repeatI(n - 5))
            .partialToRomanConverter(
                    n -> n == 9,
                    n -> "IX")
            .build();

    public static String numberToRoman(final int number) {

        if (number < 1 || number > 3000) {
            throw new IllegalArgumentException();
        }

        final List<Integer> digits = toDigits(number);
        final StringBuilder result = new StringBuilder();
        if (digits.size() >= 2) {
            final Integer secondDigit = digits.get(1);
            final String secondDigitConverted =
                    bellow10Converter.apply(secondDigit).orElse("")
                            .replaceAll("X", "C")
                            .replaceAll("V", "L")
                            .replaceAll("I", "X");
            result.append(secondDigitConverted);
        }
        final Integer firstDigit = digits.get(0);
        final String firstDigitConverted = bellow10Converter.apply(firstDigit).orElse("");
        result.append(firstDigitConverted);
        return result.toString();
    }

    private static String repeatI(int times) {
        return IntStream.rangeClosed(1, times)
                .mapToObj(i -> "I")
                .collect(Collectors.joining());
    }
}

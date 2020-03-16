package fr.ninauve.renaud.kata.romannumerals;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class RomanNumerals {

    private static final PartialToRomanConverterComposite bellow10PartialToRomanConverters = new PartialToRomanConverterComposite.Builder()
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
                    n -> "V" + repeatI(n-5))
            .partialToRomanConverter(
                    n -> n == 9,
                    n -> "IX")
        .build();

    private static final PartialToRomanConverterComposite partialToRomanConverter = new PartialToRomanConverterComposite.Builder()
            .partialToRomanConverter(
                    n -> n < 1 || n > 3000,
                    n -> {
                        throw new IllegalArgumentException();
                    })
            .partialToRomanConverters(bellow10PartialToRomanConverters)
            .partialToRomanConverter(
                    n -> n == 10,
                    n -> "X"
            )
            .partialToRomanConverter(
                    n -> n > 10 && n <= 19,
                    n -> "X" + bellow10PartialToRomanConverters.convert(n - 10))
            .build();

    public static String numberToRoman(final int number) {

        return partialToRomanConverter.convert(number);
    }

    private static String repeatI(int times) {
        return IntStream.rangeClosed(1, times)
                .mapToObj(i -> "I")
                .collect(Collectors.joining());
    }

}

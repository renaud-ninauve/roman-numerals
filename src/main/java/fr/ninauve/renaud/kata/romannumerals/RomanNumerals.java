package fr.ninauve.renaud.kata.romannumerals;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static fr.ninauve.renaud.kata.romannumerals.ToDigits.toDigits;

public final class RomanNumerals {

    private static final Map<String, String> REPLACEMENTS_FOR_TENS = new LinkedHashMap<>();

    static {
        REPLACEMENTS_FOR_TENS.put("X", "C");
        REPLACEMENTS_FOR_TENS.put("V", "L");
        REPLACEMENTS_FOR_TENS.put("I", "X");
    }

    private static final Map<String, String> REPLACEMENTS_FOR_HUNDREDS = new LinkedHashMap<>();

    static {
        REPLACEMENTS_FOR_HUNDREDS.put("X", "M");
        REPLACEMENTS_FOR_HUNDREDS.put("V", "D");
        REPLACEMENTS_FOR_HUNDREDS.put("I", "C");
    }

    private static final Map<String, String> REPLACEMENTS_FOR_THOUSANDS = new LinkedHashMap<>();

    static {
        REPLACEMENTS_FOR_THOUSANDS.put("I", "M");
    }

    private static final Function<Integer, String> CONVERTER_FOR_BELLOW_10 = new ToRomanBuilder()
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

    private static final Function<Integer, String> CONVERTER_FOR_TENS =
            converterWithReplacements(REPLACEMENTS_FOR_TENS);
    private static final Function<Integer, String> CONVERTER_FOR_HUNDREDS =
            converterWithReplacements(REPLACEMENTS_FOR_HUNDREDS);
    private static final Function<Integer, String> CONVERTER_FOR_THOUSANDS =
            converterWithReplacements(REPLACEMENTS_FOR_THOUSANDS);

    public static String numberToRoman(final int number) {

        if (number < 1 || number > 3000) {
            throw new IllegalArgumentException();
        }

        final List<Integer> digits = toDigits(number);
        final StringBuilder result = new StringBuilder();
        if (digits.size() >= 4) {
            final Integer thousands = digits.get(3);
            final String convertedThousands = CONVERTER_FOR_THOUSANDS.apply(thousands);
            result.append(convertedThousands);
        }
        if (digits.size() >= 3) {
            final Integer hundreds = digits.get(2);
            final String convertedHundreds = CONVERTER_FOR_HUNDREDS.apply(hundreds);
            result.append(convertedHundreds);
        }
        if (digits.size() >= 2) {
            final Integer tens = digits.get(1);
            final String convertedTens = CONVERTER_FOR_TENS.apply(tens);
            result.append(convertedTens);
        }
        final Integer firstDigit = digits.get(0);
        final String firstDigitConverted = CONVERTER_FOR_BELLOW_10.apply(firstDigit);
        result.append(firstDigitConverted);
        return result.toString();
    }

    private static Function<Integer, String> converterWithReplacements(final Map<String, String> replacements) {
        return CONVERTER_FOR_BELLOW_10.andThen(s -> replaceLetters(replacements, s));
    }

    private static String replaceLetters(Map<String, String> replacements, String str) {
        String result = str;
        for (Map.Entry<String, String> replacement : replacements.entrySet()) {
            result = result.replaceAll(replacement.getKey(), replacement.getValue());
        }
        return result;
    }

    private static String repeatI(int times) {
        return IntStream.rangeClosed(1, times)
                .mapToObj(i -> "I")
                .collect(Collectors.joining());
    }
}

package fr.ninauve.renaud.kata.romannumerals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class RomanNumerals {

    private static List<PartialToRomanConverter> partialToRomanConverters = new PartialToRomanConverter.PartialToRomanConvertersBuilder()
            .partialToRomanConverter(
                    n -> n < 1 || n > 3000,
                    n -> {
                        throw new IllegalArgumentException();
                    })
            .partialToRomanConverter(
                    n -> n <= 3,
                    n -> IntStream.rangeClosed(1, n)
                            .mapToObj(i -> "I")
                            .collect(Collectors.joining()))
            .partialToRomanConverter(
                    n -> n == 4,
                    n -> "IV")
            .partialToRomanConverter(
                    n -> n == 5,
                    n -> "V")
            .build();

    public static String numberToRoman(final int number) {

        return partialToRomanConverters.stream()
                .filter(c -> c.isApplicableTo(number))
                .findFirst()
                .map(c -> c.convert(number))
                .orElse(null);
    }

    private static class PartialToRomanConverter {
        private final Predicate<Integer> applicableTo;
        private final Function<Integer, String> convert;

        private static class PartialToRomanConvertersBuilder {
            private final List<PartialToRomanConverter> partialToRomanConverters = new ArrayList<>();

            private PartialToRomanConvertersBuilder partialToRomanConverter(Predicate<Integer> applicableTo,
                                                                            Function<Integer, String> convert) {
                this.partialToRomanConverters.add(new PartialToRomanConverter(applicableTo, convert));
                return this;
            }

            private List<PartialToRomanConverter> build() {
                return partialToRomanConverters;
            }
        }

        private PartialToRomanConverter(Predicate<Integer> applicableTo,
                                        Function<Integer, String> convert) {
            this.applicableTo = applicableTo;
            this.convert = convert;
        }

        public boolean isApplicableTo(int number) {
            return applicableTo.test(number);
        }

        public String convert(int number) {
            return convert.apply(number);
        }
    }
}

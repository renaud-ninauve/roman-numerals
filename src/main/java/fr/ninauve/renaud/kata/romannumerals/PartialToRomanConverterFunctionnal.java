package fr.ninauve.renaud.kata.romannumerals;

import java.util.function.Function;
import java.util.function.Predicate;

public final class PartialToRomanConverterFunctionnal implements PartialToRomanConverter {
    private final Predicate<Integer> applicableTo;
    private final Function<Integer, String> convert;

    public PartialToRomanConverterFunctionnal(Predicate<Integer> applicableTo,
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

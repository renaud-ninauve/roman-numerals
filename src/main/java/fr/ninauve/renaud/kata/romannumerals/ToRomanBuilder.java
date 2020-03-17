package fr.ninauve.renaud.kata.romannumerals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ToRomanBuilder {
    private List<Function<Integer, Optional<String>>> converters = new ArrayList<>();

    public ToRomanBuilder partialToRomanConverter(Predicate<Integer> applicableTo,
                                                  Function<Integer, String> convert) {
        final Function<Integer, Optional<String>> converter =
                n -> applicableTo.test(n) ? Optional.of(convert.apply(n)) : Optional.empty();
        this.partialToRomanConverter(converter);
        return this;
    }

    public ToRomanBuilder partialToRomanConverter(Function<Integer, Optional<String>> converter) {
        this.converters.add(converter);
        return this;
    }

    public Function<Integer, String> build() {
        return n -> converters.stream()
                .map(c -> c.apply(n))
                .flatMap(Optional::stream)
                .findFirst()
                .orElse("");
    }
}

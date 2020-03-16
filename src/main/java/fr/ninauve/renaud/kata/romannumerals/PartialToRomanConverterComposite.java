package fr.ninauve.renaud.kata.romannumerals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class PartialToRomanConverterComposite {

    public static final class Builder {
        private List<Function<Integer, Optional<String>>> converters = new ArrayList<>();

        public Builder partialToRomanConverter(Predicate<Integer> applicableTo,
                                               Function<Integer, String> convert) {
            final Function<Integer, Optional<String>> converter =
                    n -> applicableTo.test(n) ? Optional.of(convert.apply(n)) : Optional.empty();
            this.partialToRomanConverter(converter);
            return this;
        }

        public Builder partialToRomanConverter(Function<Integer, Optional<String>> converter) {
            this.converters.add(converter);
            return this;
        }

        public Function<Integer, Optional<String>> build() {
            return n -> converters.stream()
                    .map(c -> c.apply(n))
                    .filter(Optional::isPresent)
                    .findFirst()
                    .orElse(Optional.empty());
        }
    }
}

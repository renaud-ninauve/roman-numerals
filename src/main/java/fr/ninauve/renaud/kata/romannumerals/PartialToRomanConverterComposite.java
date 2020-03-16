package fr.ninauve.renaud.kata.romannumerals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class PartialToRomanConverterComposite implements PartialToRomanConverter {
    private final List<PartialToRomanConverter> partialToRomanConverters;

    public PartialToRomanConverterComposite(List<PartialToRomanConverter> partialToRomanConverters) {
        this.partialToRomanConverters = partialToRomanConverters;
    }

    @Override
    public boolean isApplicableTo(int number) {
        return partialToRomanConverters.stream()
                .anyMatch(c -> c.isApplicableTo(number));
    }

    @Override
    public String convert(int number) {
        return partialToRomanConverters.stream()
                .filter(c -> c.isApplicableTo(number))
                .findFirst()
                .map(c -> c.convert(number))
                .orElse(null);
    }

    public static final class Builder {
        private final List<PartialToRomanConverter> partialToRomanConverters = new ArrayList<>();

        public Builder partialToRomanConverter(Predicate<Integer> applicableTo,
                                               Function<Integer, String> convert) {
            this.partialToRomanConverters.add(new PartialToRomanConverterFunctionnal(applicableTo, convert));
            return this;
        }

        public Builder partialToRomanConverters(PartialToRomanConverter partialToRomanConverter) {
            this.partialToRomanConverters.add(partialToRomanConverter);
            return this;
        }

        public PartialToRomanConverterComposite build() {
            return new PartialToRomanConverterComposite(partialToRomanConverters);
        }
    }
}

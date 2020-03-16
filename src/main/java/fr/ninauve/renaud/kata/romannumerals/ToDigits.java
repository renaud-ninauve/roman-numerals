package fr.ninauve.renaud.kata.romannumerals;

import java.util.ArrayList;
import java.util.List;

public final class ToDigits {

    public static List<Integer> toDigits(int number) {

        final String numberStr = Integer.toString(number);
        final List<Integer> digits = new ArrayList<>();
        for (int i=numberStr.length()-1; i>=0; i--) {
            digits.add(Integer.parseInt("" + numberStr.charAt(i)));
        }
        return digits;
    }
}

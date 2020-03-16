package fr.ninauve.renaud.kata.romannumerals;

interface PartialToRomanConverter {

    boolean isApplicableTo(int number);

    String convert(int number);
}

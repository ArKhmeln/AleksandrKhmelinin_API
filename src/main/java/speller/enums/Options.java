package speller.enums;

public enum Options {

    IGNORE_DIGITS(2),
    IGNORE_URLS(4),
    FIND_REPEAT_WORDS(8),
    IGNORE_CAPITALIZATION(512);

    final int number;

    Options(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

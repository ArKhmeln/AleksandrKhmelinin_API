package speller.enums;

public enum Errors {

    ERROR_UNKNOWN_WORD("1"),
    ERROR_REPEAT_WORD("2"),
    ERROR_CAPITALIZATION("3"),
    ERROR_TOO_MANY_ERRORS("4");

    final String number;

    Errors(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}

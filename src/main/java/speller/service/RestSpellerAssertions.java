package speller.service;

import speller.dto.SpellerDto;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RestSpellerAssertions {

    private SpellerDto[] textAnswer;

    private SpellerDto[][] textsAnswer;

    public RestSpellerAssertions(SpellerDto[] response) {
        this.textAnswer = response;
    }

    public RestSpellerAssertions(SpellerDto[][] response) {
        this.textsAnswer = response;
    }

    public void assertTextCorrectionRequest(String word) {
        assertTrue(textAnswer[0].getS().contains(word));
    }

    public void assertRightInitilalWord(String word) {
        assertEquals(textAnswer[0].getWord(), word, "Wrong word");
    }

    public void assertRightNumberOfSuggestions(int  numberOfSuggestions) {
        assertEquals(textAnswer[0].getS().size(), numberOfSuggestions, "Wrong number of suggestions");
    }

    public void assertRightInitialWordLength(String length) {
        assertEquals(textAnswer[0].getLen(), length, "Wrong initial word length");
    }

    public void assertRightErrorCode(String errorCode) {
        assertEquals(textAnswer[0].getCode(), errorCode, "Wrong error code");
    }

    public void assertTextsCorrectionRequest(String text) {
        assertTrue(textsAnswer[0][0].getS().contains(text), "No suggestions");
    }
}

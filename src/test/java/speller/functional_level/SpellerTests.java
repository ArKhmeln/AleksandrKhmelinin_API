package speller.functional_level;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import speller.dto.SpellerDto;
import speller.enums.Errors;
import speller.enums.Options;
import speller.service.RestSpellerAssertions;
import speller.service.RestSpellerSteps;

import static speller.enums.Errors.*;
import static speller.enums.Options.*;

public class SpellerTests {

    @Test(description = "right suggestion for checkText")
    void rightSuggestionForTextTest() {
        SpellerDto[] speller = new RestSpellerSteps().checkText("rght");

        RestSpellerAssertions result = new RestSpellerAssertions(speller);
            result.assertRightInitilalWord("rght");
            result.assertTextCorrectionRequest("right");
            result.assertTextCorrectionRequest("rights");
    }

    @Test(description = "right result attributes")
    void resultAttributesTest() {
        SpellerDto[] speller = new RestSpellerSteps().checkText("hllo");

        RestSpellerAssertions result = new RestSpellerAssertions(speller);
            result.assertTextCorrectionRequest("hello");
            result.assertRightNumberOfSuggestions(3);
            result.assertRightInitialWordLength("4");
    }

    @DataProvider(name = "errors")
    public static Object[][] errors() {
        return new Object[][]
                        {{"синхрофазатрон", ERROR_UNKNOWN_WORD},
                        {"repeatrepeat", ERROR_REPEAT_WORD},  //this error code won't be displayed
                        {"дубне", ERROR_CAPITALIZATION}};    //will fail despite this example is in the documentation
    }

    @Test(description = "right suggestion for checkTexts")
    void rightSuggestionForTextsTest() {
        SpellerDto[][] speller = new RestSpellerSteps().checkTexts("lke", "hllo");

        RestSpellerAssertions result = new RestSpellerAssertions(speller);
        result.assertTextsCorrectionRequest("[\"like", 0);
        result.assertTextsCorrectionRequest("hello", 1);
    }

    @Test(dataProvider = "errors")
    public void errorCodesTest(String text, Errors errorCode) {
        SpellerDto[] speller = new RestSpellerSteps().checkText(text);

        RestSpellerAssertions result = new RestSpellerAssertions(speller);
            result.assertRightErrorCode(errorCode.getNumber());
    }

    @DataProvider(name = "options")
    public static Object[][] texts() {
        return new Object[][]
                        {{"pr0duction", IGNORE_DIGITS},     //speller still won't ignore it
                        {"mail@yandex.ru1", IGNORE_DIGITS, IGNORE_URLS},   //speller won't see it
                        {"я полетел на на Кипр", FIND_REPEAT_WORDS},    //speller won't see it despite it's an example in the doc
                        {"москва", IGNORE_CAPITALIZATION}};   //speller won't see it despite it's an example in the doc
    }

    @Test(dataProvider = "options")
    public void textWithOptionsTest(String text, Options ... option) {
        SpellerDto[] speller = new RestSpellerSteps().checkTextWithOptions(text, option);

        RestSpellerAssertions result = new RestSpellerAssertions(speller);
        result.assertRightNumberOfSuggestions(0);
    }
}

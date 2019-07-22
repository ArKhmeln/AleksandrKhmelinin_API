package speller.functional_level;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import speller.dto.SpellerDto;
import speller.service.RestSpellerAssertions;
import speller.service.RestSpellerSteps;

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
                        {{"синхрофазатрон", "1"},
                        {"repeatrepeat", "2"},  //this error code won't be displayed
                        {"дубне", "3"}};    //will fail despite this example is in the documentation
    }

    @Test(description = "right suggestion for checkTexts")
    void rightSuggestionForTextsTest() {
        SpellerDto[][] speller = new RestSpellerSteps().checkTexts("i lke to play volleyball");

        RestSpellerAssertions result = new RestSpellerAssertions(speller);
        result.assertTextsCorrectionRequest("like");
    }

    @Test(dataProvider = "errors")
    public void errorCodesTest(String text, String errorCode) {
        SpellerDto[] speller = new RestSpellerSteps().checkText(text);

        RestSpellerAssertions result = new RestSpellerAssertions(speller);
            result.assertRightErrorCode(errorCode);
    }

    @DataProvider(name = "options")
    public static Object[][] texts() {
        return new Object[][]
                        {{"pr0duction", 2},     //speller still won't ignore it
                        {"mail@yandex.ru", 4},   //speller won't see it
                        {"я полетел на на Кипр", 8},    //speller won't see it despite it's an example in the doc
                        {"москва", 512}};   //speller won't see it despite it's an example in the doc
    }

    @Test(dataProvider = "options")
    public void textWithOptionsTest(String text, int option) {
        SpellerDto[] speller = new RestSpellerSteps().checkTextWithOptions(text, option);

        RestSpellerAssertions result = new RestSpellerAssertions(speller);
        result.assertRightNumberOfSuggestions(0);
    }
}

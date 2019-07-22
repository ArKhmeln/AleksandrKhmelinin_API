package speller.low_level;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SimpleSpellerTests {

    private RequestSpecification REQUEST_SPECIFICATION;
    private Properties property = new Properties();
    private String domain;
    private String checkText;
    private String checkTexts;

    private void dataFromProperties() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            property.load(fis);
            domain = property.getProperty("domain");
            checkText = domain + property.getProperty("checkText");
            checkTexts = domain + property.getProperty("checkTexts");
        } catch (IOException e) {
            System.err.println("ERROR: Property file not found!");
        }
    }

    @BeforeMethod
    public void setup() {
        REQUEST_SPECIFICATION = new RequestSpecBuilder()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter()).build();
        dataFromProperties();
    }

    @Test(description = "word correction check")
    void wordCorrectionTest() {
        given(REQUEST_SPECIFICATION)
                .param("text", "destny")
                .get(checkText)
                .then()
                .body("word[0]", is("destny"))
                .body("s[0][0]", is("destiny"));
    }

    @Test(description = "right number of suggestions")
    void numberOfSuggestionsTest() {
        given(REQUEST_SPECIFICATION)
                .param("text", "hllo")
                .get(checkText)
                .then()
                .body("s[0]", hasSize(3));
    }

    @Test(description = "result has proper attributes")
    void properAttributesInResultTest() {
        given(REQUEST_SPECIFICATION)
                .param("text", "distrb")
                .get(checkText)
                .then()
                .body("code[0]", is(1))
                .body("len[0]", is(6));
    }

    @Test(description = "word with digit check")
    void wordWithDigitTest() {
        given(REQUEST_SPECIFICATION)
                .param("text", "pr0duction")
                .get(checkText)
                .then()
                .body("s[0][0]", is("production"));
    }

    @Test(description = "check text fragment correction")
    void textFragmentCorrectionTest() {
        given(REQUEST_SPECIFICATION)
                .param("text", "there are a lot of leters")
                .get(checkTexts)
                .then()
                .body("s[0][0]", hasItem("letters"));
    }
}

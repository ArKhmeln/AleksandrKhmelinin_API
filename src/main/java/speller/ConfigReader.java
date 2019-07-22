package speller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    protected Properties property = new Properties();

    protected String domain;
    protected String checkTextUri;
    protected String checkTextsUri;

    protected void dataFromProperties() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            property.load(fis);
            domain = property.getProperty("domain");
            checkTextUri = property.getProperty("checkText");
            checkTextsUri = property.getProperty("checkTexts");
        } catch (IOException e) {
            System.err.println("ERROR: Property file not found!");
        }
    }
}

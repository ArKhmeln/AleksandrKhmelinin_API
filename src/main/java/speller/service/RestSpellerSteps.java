package speller.service;

import com.google.gson.Gson;
import speller.ConfigReader;
import speller.dto.SpellerDto;

import java.util.HashMap;

public class RestSpellerSteps extends ConfigReader {

    public RestSpellerSteps() {
        dataFromProperties();
    }

    public SpellerDto[] checkText(String param) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("text", param);

        return
                new Gson().fromJson(
                        new RestSpellerService()
                                .getWithParams(checkTextUri, params)
                                .getBody().asString(), SpellerDto[].class);
    }

    public SpellerDto[] checkTextWithOptions(String param, int option) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("text", param);
        params.put("option", option);

        return
                new Gson().fromJson(
                        new RestSpellerService()
                                .getWithParams(checkTextUri, params)
                                .getBody().asString(), SpellerDto[].class);
    }

    public SpellerDto[][] checkTexts(String text) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("text", text);

        return
                new Gson().fromJson(
                        new RestSpellerService()
                                .getWithParams(checkTextsUri, params)
                                .getBody().asString(), SpellerDto[][].class);
    }
}

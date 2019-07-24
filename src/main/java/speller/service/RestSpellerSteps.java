package speller.service;

import com.google.gson.Gson;
import speller.ConfigReader;
import speller.dto.SpellerDto;
import speller.enums.Options;

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

    public SpellerDto[] checkTextWithOptions(String param, Options ... options) {
        HashMap<String, Object> params = new HashMap<>();
        int optionsSum = 0;
        for (Options option : options) {
            optionsSum += option.getNumber();
        }
        params.put("text", param);
        params.put("option", optionsSum);

        return
                new Gson().fromJson(
                        new RestSpellerService()
                                .getWithParams(checkTextUri, params)
                                .getBody().asString(), SpellerDto[].class);
    }

    public SpellerDto[][] checkTexts(String ... text) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("text", text);

        return
                new Gson().fromJson(
                        new RestSpellerService()
                                .getWithParams(checkTextsUri, params)
                                .getBody().asString(), SpellerDto[][].class);
    }
}

package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;

import java.util.Set;

public class TestBase {
    @BeforeClass
    public void init(){
        RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==","");
    }

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) {
        String json = RestAssured.get("http://demo.bugify.com/api/issues/"+ String.valueOf(issueId) +".json").asString();
        JsonElement parsed = new JsonParser().parse(json).getAsJsonObject().get("issues");
        Set fromJson = new Gson().fromJson(parsed, new TypeToken<Set<Issue>>(){}.getType());
        Issue next = (Issue) fromJson.iterator().next();
        if(next.getState() == 3){
            return false;
        }
        return true;
    }


}

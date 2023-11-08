package com.api.tests;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreateWorkSpaces extends TestBase {
    /*@BeforeMethod
    public void setup(){
        RequestSpecBuilder requestSpecBuilderbuilder = new RequestSpecBuilder();
        requestSpecBuilderbuilder.setBaseUri("https://api.getpostman.com");
        requestSpecBuilderbuilder.addHeader("x-api-key","PMAK-619254d610962f00510fa7b5-c7692fcb4c19c102704fcbf34dad069dc8");
        requestSpecBuilderbuilder.log(LogDetail.ALL);
        requestSpecification = requestSpecBuilderbuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.log(LogDetail.ALL);
        //responseSpecBuilder.expectStatusCode(200);
        responseSpecification = responseSpecBuilder.build();
    }*/


    @Test
    public void create_work_space(){
        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"My Workspace5\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"This is where the collaboration happens. Use this space to share and collaborate on APIs, collections, environments, monitors, and mocks.\"\n" +
                "        \n" +
                "    }\n" +
                "}";
        String workSpaceName = RandomStringUtils.randomAlphanumeric(7);
        Map<String,Object> mainObject = new HashMap<>();
        Map<String,String> childObject = new HashMap<>();
        childObject.put("name",workSpaceName);
        childObject.put("type","team");
        childObject.put("description","new workspace created");
        mainObject.put("workspace",childObject);

        Response response = given().
                body(mainObject).
        when().
                post("/workspaces").
        then().
                assertThat().
                statusCode(200).
                body("workspace.name",equalTo(workSpaceName),
                        "workspace",hasKey("id"),
                        "workspace",hasKey("name")).
                extract().response();



    }

}

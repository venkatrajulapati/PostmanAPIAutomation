package com.api.tests;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class DeleteWorkSpaces {
    @BeforeMethod
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
    }


    @Test
    public void delete_work_space(){
        String workspaceName = RandomStringUtils.randomAlphanumeric(7);

        Map<String,Object> mainObject = new HashMap<>();
        Map<String,String> childObject = new HashMap<>();
        childObject.put("name",workspaceName);
        childObject.put("type","team");
        childObject.put("description","new workspace created");
        mainObject.put("workspace",childObject);

        //File f = new File("src/main/resources/CreateWorkspace.json");


        Response response = given().
                body(mainObject).
        when().
                post("/workspaces").
        then().
                assertThat().
                statusCode(200).
                body("workspace.name",equalTo(workspaceName),
                        "workspace",hasKey("id"),
                        "workspace",hasKey("name")).
                extract().response();

        JsonPath jpath = new JsonPath(response.asString());
        String workspaceID = jpath.getString("workspace.id");

        Response resp = with().pathParam("workspaceid",workspaceID).delete("/workspaces/{workspaceid}");
        assertThat(resp.statusCode(),equalTo(200));
        assertThat(resp.statusCode(),equalTo(200));
        jpath = new JsonPath(resp.asString());
        assertThat(jpath.getString("workspace.id"),equalTo(workspaceID));

    }

}

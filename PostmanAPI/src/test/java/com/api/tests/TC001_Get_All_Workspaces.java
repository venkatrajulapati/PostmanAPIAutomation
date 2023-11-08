package com.api.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TC001_Get_All_Workspaces {

    //RequestSpecification requestSpecification;

    @BeforeMethod
    public void setRequestSpecification(){
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
    public void getAllWorkspaces(){

        given().

        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200);

    }

    @Test
    public void getWorkSpaceById(){
        String workSpaceID = "a0a90899-5354-4780-834e-caf4555b4ec3";
        Response response = with().pathParam("workspaceId",workSpaceID).get("/workspaces/{workspaceId}");
        //assertThat(response.statusCode(),is(equalTo(200)));

    }
    @Test
    public void getWorkSpacesInvalidId(){
        String workSpaceID = "a0a90899-5354-4780-834e-caf455ec3";
        given().
                pathParam("workspaceId",workSpaceID).
        when().
                get("/workspaces/{workspaceId}").
        then().
                assertThat().statusCode(404).
                body("error.message",equalTo("Workspace not found"));
    }

    @Test
    public void getWorkSpacesInvalidIdNonBDD(){
        String workSpaceID = "a0a90899-5354-4780-834e-caf455ec3";
        Response response = with().pathParam("workspaceId",workSpaceID).get("/workspaces/{workspaceId}");
        JsonPath jpath = new JsonPath(response.asString());
        Map<String,Object> map = jpath.getMap("error");
        assertThat(map,hasKey("name"));
        assertThat(map,hasKey("message"));
        assertThat(map,hasKey("statusCode"));
        assertThat(response.statusCode(),equalTo(404));

    }


}

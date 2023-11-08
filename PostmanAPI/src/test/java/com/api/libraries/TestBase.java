package com.api.libraries;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;

public class TestBase {

    @BeforeMethod
    public void setup(){
        RequestSpecBuilder requestSpecBuilderbuilder = new RequestSpecBuilder();
        requestSpecBuilderbuilder.setBaseUri(Constants.BASE_URL);
        requestSpecBuilderbuilder.addHeader("x-api-key",Constants.API_KEY);
        requestSpecBuilderbuilder.log(LogDetail.ALL);
        requestSpecification = requestSpecBuilderbuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.log(LogDetail.ALL);
        //responseSpecBuilder.expectStatusCode(200);
        responseSpecification = responseSpecBuilder.build();
    }
}

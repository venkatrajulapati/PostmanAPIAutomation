package com.api.tests;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeMethod;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestBase {

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
}

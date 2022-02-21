package post_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Post02 extends JsonPlaceHolderBaseUrl {

/*
     Given
            "https://jsonplaceholder.typicode.com/todos"

                {"userId": 55,
                "title": "Tidy your room",
                "completed": false,
                }
       When
           User sends POST request to the Url
        Then
            Status code is 201
        And
            Response body should be like that;
                {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false,
                "id ":201

                }
     */

    @Test
    public void post02(){

        //1st Step: Set the url

        spec.pathParams("firstparameter","todos");

        //2nd Step: Set the expected data

        // 201 is for POST request ---new data created successfully

        // I should not put test data to the testing area
        // so we separated data from test method
        JsonPlaceHolderTestData expectedData= new JsonPlaceHolderTestData();
        Map<String,Object>expectedDataMap=  expectedData.expectedDataSetUp();


        // 3rd Step: Send the request get the response

        Response response=given().
                                    spec(spec).
                                    auth().basic("admin","1234").
                                    contentType(ContentType.JSON).
                                    body(expectedDataMap).
                            when().
                                    post("/{firstparameter}");
        response.prettyPrint();

        // I added StatusCode to use in assertions

        expectedDataMap.put("StatusCode",201);

        // 4th Step: Make assertions

        Map <String, Object> actualData= response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(expectedDataMap.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedDataMap.get("userId"), actualData.get("userId"));
        assertEquals(expectedDataMap.get("title"), actualData.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualData.get("completed"));


    }
}



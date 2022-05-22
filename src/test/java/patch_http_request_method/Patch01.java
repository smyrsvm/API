package patch_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.cucumber.java.it.Ma;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {



    @Test
    public void patch01 (){

        /*
     Given
            "https://jsonplaceholder.typicode.com/todos/198"

                {
                "title": "Wash the dishes",
                }
       When
           User sends PATCH request to the Url
        Then
            Status code is 200
        And
            Response body should be like that;
                {
                "userId": 10,
                "title": "Wash the dishes",
                "completed": true,
                 "id ":198
                }
     */

        //1st Step: Set the url

        spec.pathParams("firstparameter","todos","secondparameter",198);

        //2nd Step: Set the request body data
        
        JsonPlaceHolderTestData requestBody= new JsonPlaceHolderTestData();
        Map<String,Object> requestBodyMap=requestBody.expectedDataSetUpWithMissingKeys(null,"Wash the dishes",null);
        System.out.println(requestBodyMap);

        // null is an Object, so primitives do not accept...


        //3rd Step: Send the request and get the response

        Response response=given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().patch("/{firstparameter}/{secondparameter}");
        response.prettyPrint();

        // 4th Step: Make assertion

            // 1st Logic: No need to verify data that you did not touch

            response.then().assertThat().statusCode(200).body("title", equalTo(requestBodyMap.get("title")));

            // 2nd Logic: RequestBody is different from expectedData

            Map<String,Object> expectedDataMap=requestBody.expectedDataSetUpWithAllKeys(10,"Wash the dishes", true);
            response.then().assertThat().statusCode(200).body("userId", equalTo(expectedDataMap.get("userId")),
                "title",equalTo(expectedDataMap.get("title")),"completed", equalTo(expectedDataMap.get("completed")));


        // Assertion with GSON

        Map<String,Object> actualDataMap= response.as(HashMap.class);// convert Json Data(It is in "response") to HashMap

        response.then().assertThat().statusCode(200);

        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));

    }

}

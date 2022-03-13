package put_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class put01 extends JsonPlaceHolderBaseUrl {

    /*
     Given
            "https://jsonplaceholder.typicode.com/todos/198"

                {"userId": 21,
                "title": "Wash the dishes",
                "completed": false,
                }
       When
           User sends PUT request to the Url
        Then
            Status code is 200
        And
            Response body should be like that;
                {
                "userId": 21,
                "title": "Wash the dishes",
                "completed": false,
                       "id ":201

                }
     */

    @Test
    public void put01(){

        //First Step:Set the url

        spec.pathParams("firstparameter","todos","secondparameter",198);

        // 2nd Step: Set the request body

        JsonPlaceHolderTestData requestBody= new JsonPlaceHolderTestData();
        Map<String,Object> requestBodyMap= requestBody.expectedDataSetUpWithAllKeys(21,"Wash the dishes",false);

        // 3rd Step: Send request and get response

        Response response=given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().put("/{firstparameter}/{secondparameter}");
        response.prettyPrint();

         // 4th Step: Make assertions

        //1st Way:
        // We used response body...
        response.
                then().
                assertThat().
                statusCode(200).body("userId",equalTo(requestBodyMap.get("userId")),
                        "title",equalTo(requestBodyMap.get("title")),"completed",equalTo(requestBodyMap.get("completed")));


        //2nd Way: Use GSON
        // GSON uses deserialization or serialization .
        // We will use here deserialization_converting response which is in Json data to java Object

        Map<String,Object> actualDataMap= response.as(HashMap.class);

        assertEquals(requestBodyMap.get("userId"), actualDataMap.get("userId"));
        assertEquals(requestBodyMap.get("title"), actualDataMap.get("title"));
        assertEquals(requestBodyMap.get("completed"), actualDataMap.get("completed"));

        // How to use GSON in Serialization

        Map<String,Integer> ages= new HashMap<>();
        ages.put("Ali Can",13);
        ages.put("Veli Han",15);
        ages.put("Ayse Kan",21);
        ages.put("Mary Star",65);
        System.out.println(ages); // {Mary Star=65, Ayse Kan=21, Ali Can=13, Veli Han=15}
        // this is map; no double quotes and there is equal sign

        // Convert ages map to json data

        // 1st Step: Create GSON Object_ to access the methods in GSON class
        Gson gson = new Gson();

        // 2nd Step: By using "gson" object we select "toJson()" method to convert Java Object to Json Data
        String jsonFromMap=gson.toJson(ages);

        System.out.println(jsonFromMap); //{"Mary Star":65,"Ayse Kan":21,"Ali Can":13,"Veli Han":15}
        //It is now json; there is double quote and colon(iki nokta ustuste)




    }

}

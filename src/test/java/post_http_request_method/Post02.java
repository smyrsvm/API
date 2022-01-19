package post_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

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

        // 1st: Set the url

        spec.pathParam("firstparameter","todos");

        // 2nd: Set the expected data

        Map<String,Object> expectedData= new HashMap<>();
        expectedData.put("userId",55);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);
        expectedData.put("StatusCode",201);
        System.out.println(expectedData);

        // 3rd: Send request and get response

        given().spec(spec)






        // 4th: Make assertions












    }













}

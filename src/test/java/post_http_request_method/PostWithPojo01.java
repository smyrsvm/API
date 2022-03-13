package post_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class PostWithPojo01 extends JsonPlaceHolderBaseUrl {

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
    public void postWithPojo01(){

        // 1st Step: Set the url

        spec.pathParam("firstparameter","todos");


        // 2nd Step: Set the request body

        JsonPlaceHolderPojo requestBody= new JsonPlaceHolderPojo(55,"Tidy your room",false);


        // 3rd Step: Send request and get response

        Response response= given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{firstparameter}");

        //4th Step: Do assertions

        //1st Way:

       response.then().assertThat().statusCode(201).body("userID",equalTo(requestBody.getUserID()),
                                                    "title", equalTo(requestBody.getTitle()),
                                                        "completed",equalTo(requestBody.getCompleted()));


        //2nd Way: De-Serialization

        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);

        assertEquals(requestBody.getUserID(), actualData.getUserID());
        assertEquals(requestBody.getTitle(),actualData.getTitle());
        assertEquals(requestBody.getCompleted(),actualData.getCompleted());


    }


}

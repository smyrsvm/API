package post_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class PostDeleteWithPojo03 extends JsonPlaceHolderBaseUrl {
    /*
     Given
            "https://jsonplaceholder.typicode.com/todos"

                {"userId": 55,
                "title": "Tidy your room",
                "completed": false,
                }

       When
           User sends POST request to the Url
           User sends Delete request to the url
        Then
            Response body should be like that;
                { }
     */


    @Test
    public void postDeleteWithPojo03 (){

        // You can run this test method hundred times,
        // because with delete http request you are deleting every time what you created before
        // So preventing the database unnecessarily full, after creating the data with post request;
        // do not forget to delete it

        // 1st Step: Set the url

        spec.pathParam("firstparameter","todos");

        // 2nd Step: Set the request body
        JsonPlaceHolderPojo requestBody= new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println(requestBody);

        // 3rd Step: Send request and get response

        Response response=given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{firstparameter}");
        response.prettyPrint();

        //After creating the data with post, you can do the assertions or whatever you want here
        //Then you can delete with the below written codes

        // Note: Response body has "id", I need "id" value to use "delete()".
        // So I should get "id" value from response body
        // Get the id of newly created data.
        // With JsonPath we can navigate inside the json Data. So we create a JsonPath object.
        // In every post creation, there could be a new "id".So we made it dynamic.

        JsonPath json= response.jsonPath();
        Integer id=json.getInt("id");

        // Set the url for delete request
        spec.pathParams("firstparameter","todos","secondparameter",id); //"https://jsonplaceholder.typicode.com/todos/201"

        //Send the delete request and get the response
        Response response2=given().spec(spec).when().delete("/{firstparameter}/{secondparameter}");
        response2.prettyPrint(); // {}

        // Convert response to a Map. We do not need to create a POJO.
        // Because it is an empty Json.There is no variable.There will be no getters and setters.
        // There is no data to print on the console.

        Map<String,Object> response2Map=response2.as(HashMap.class);
        // We converted empty Json to a Map to be able to use Map methods

        // 4th Step: Do the assertions

        assertTrue(response2Map.size()==0);
        assertTrue(response2Map.isEmpty());


    }


}

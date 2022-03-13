package delete_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class delete01 extends JsonPlaceHolderBaseUrl {


    @Test
    public void del01 (){


         /*
     Given
            "https://jsonplaceholder.typicode.com/todos/198"

     When
           User sends DELETE request to the Url
     Then
           Status code is 200
     And
           Response body should be like that;
                { }
     */

        //1st Step: Set the url

        spec.pathParams("firstparameter","todos","secondparameter","198");

        //2nd Step: Set the expected data

        Map<String,Object> expectedMap= new HashMap<>();
        System.out.println(expectedMap);

        //3rd : Sen the request and get the response

        Response response=given().spec(spec).contentType(ContentType.JSON).when().delete("/{firstparameter}/{secondparameter}");
        response.prettyPrint();

        //4th: Make the assertions
        response.then().statusCode(200);

        // Use GSON
        Map<String,Object> actualMap= response.as(HashMap.class);

        assertEquals(expectedMap,actualMap);
        assertTrue(actualMap.size()==0);// If we use this, we do not have to create expectedMap


    }


}

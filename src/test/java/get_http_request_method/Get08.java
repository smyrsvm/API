package get_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {

    /*
    The biggest challenge in Api Testing is data types

    1) Java uses Objects and Primitives as data type,
       API uses XML, Json etc.
       They should communicate each other but communication is not possible with different data types

       We have two options
       i) Convert json to Java Object==> De-Serialization -->we will mostly use this in getting json from API and using it in JAVA
       ii) Convert Java Object to Json==> Serialization

       For Serialization and De-Serialization, we have 2 options
       a) GSON --> Google created it
       b) Object Mapper--> created after GSON and more popular in the market

     */

      /**

      Given
           "https://jsonplaceholder.typicode.com/todos/2"
      When
           I send a GET request to the Url
       Then
           HTTP Status Code should be 200
       And
            "completed" is false
       And
          "userId" is 1
       And
           "title" is "quis ut nam facilis et officia qui"
       And
            header "Via" is "1.1 Vegur"
       And
            header "Server" is "cloudflare"

       {
       "userId": 1,
       "id": 2,
       "title": "quis ut nam facilis et officia qui",
       "completed": false
       }
       */


    @Test
    public void get08(){

        // 1. Set the url

        spec.pathParams("first", "todos", "second",2);

        //2. Set the expected data

        // When we want to convert json data to java data we prefer Maps.
        // Because maps also have key value format like json data
        // The first part of Map below is String, second part have different data types so we make it Object

        // The data comes from test case is expectedData, and the data comes from API is actual data


        Map<String,Object> expectedData= new HashMap<>();

        expectedData.put("userId",1);
        expectedData.put("id",2);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        expectedData.put("Status Code",200);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");
        System.out.println(expectedData);


        //3- Send the request and get the response

        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // By using GSON(as() method) we are able to convert Json Data which is coming from API to Java Object
        // as() method comes from GSON
        Map<String,Object> actualData= response.as(HashMap.class);
        System.out.println(actualData);

        // 4- Make assertions

        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("id"),actualData.get("id"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("Status Code"),response.getStatusCode());
        assertEquals(expectedData.get("Via"),response.getHeader("Via"));
        assertEquals(expectedData.get("Server"),response.getHeader("Server"));

    }

}

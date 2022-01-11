package get_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get03 extends JsonPlaceHolderBaseUrl {
    /*
        Given
                https://jsonplaceholder.typicode.com/todos/23
        When
            User sends GET request to the URL
        Then
            HTTP Status Code should be 200
         And
            Response format sholud be "application/json"
         And
            "title" is "et itaque necessitatibus maxime molestiae qui quas velit"
         And
                "completed" is false
         And
                "userId" is 2

     */

    @Test
    public void get03(){

        // 1.Set the url
        spec.pathParams("first", "todos", "second",23);

        // 2.Set the expected data

        // 3. Send the request and get the response

        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();// just for the response body

        // 4.Make assertions

        // Note: Instead of usung "application/json", we can use ContentType.JSON
        // ContentType is an enum. Enum is a storage for constant variables.
        // You can create like this ---> public enum enumname
        // For example you will use the states of the USA in your application. You can put these in an enum

        // completed is boolean so we didnt use double quote
        // equalTo() method can be used with every kind of data types; string, booelan, integer...very dinamic


        // First way
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed", equalTo(false)).
                body("id",equalTo(23));



        // Second way
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false),
                        "userId",equalTo(23));
        

    }

}

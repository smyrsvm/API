package get_http_request_method;

import Base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Get02 extends HerOkuAppBaseUrl {

    /*
    Given
        https://restful-booker.herookuapp.com/booking/1001
    When
        User sends GET Request to the url
    Then
        Status Code should be 404
    And
        Status  Line should be HTTP/1.1 404 Not Found
    And
        Response body contains "Not Found"
    And
        Server is "Cowboy"
     */

    // Note; Path parameters are used to make resource smaller

    @Test //We are using Junit test annotation
    public void get02(){

        //1st Step: Set the url

        // String url="https://restful-booker.herookuapp.com/booking/1001"; --> Not recommended

        // https://restful-booker.herookuapp.com ---> is in spec object
        // The others are path parameters. We have 2 path parameters
        // If you have more than one parameter select the plural---> pathParams not pathParam

        spec.pathParams("first","booking","second",1001); // with this code i added booking and 1001 to baseurl

        // 2: Set the expected data

        // 3. Step: send request and get response

        // We were making like this before--> Response response= given().when().get(url);

        Response response = given().spec(spec).when().get("/{first},/{second}");

        response.prettyPrint();

        //4. Assertions

        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        //  The question is --> And Response body contains "Not Found"

        /** response has json data
         response.asString().contains("Not Found");// That code will give me true or false. we can print it

         System.out.println(response.asString().contains("Not Found"));// true

         But when you are doing automation tests, seeing "true" on the console means nothing */

        // assertTrue(true)==> Green thick  assertTrue(False)==> red cross
        // if it is assertTrue getting true is a success
        assertTrue(response.asString().contains("Not Found")); // assertTrue from TestNg

        // assertFalse(false)==> Green thick  assertFalse(True)==> red cross
        // if it is assertFalse getting false is a success
        assertFalse(response.asString().contains("TechProed")); // Green Thick

        // Expected data comes from test case, actual data comes from API
        // assertEquals() returns true (test passes) if the arguments match
        assertEquals("Cowboy", response.getHeader("Server"));
    }


}

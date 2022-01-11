package get_http_request_method;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {

    /*
    Gherkin Language will be used
    Keywords like Given, And, When, Then  are used
    For example you want to use two when or two Then etc---> make the second of them "And"
    Given: It declares prerequisites (önkosul)
    When: It is used to declare actions
    Then: It is for outputs
    And: is used for multiple When, Given, Then

    -----------------------------------------------------------
    Given
        https://restful-booker.herokuapp.com/booking/3
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be "HTTP/1.1 200 OK"


     */

    // create a test method for the test cases
    // In tests, instead of main method, we use annotations.Test Annotation from Junit
    // JUNit is a framework used for testing.

    @Test
    public void get01 (){

        // 1) Set the url(endpoint)

        String url="https://restful-booker.herokuapp.com/booking/3"; //in a primitive way

        // 2) Set the expected data

        // 3) Type automation script to send Get Request and to get response

        /**given().when().get(url) ---> that code sends the request to the API in that url
         and we get response actually from this code */

        Response response= given().when().get(url);

        response.prettyPrint(); //We print response body

        // 4) Make assertions
        // If you have multiple errors in assertions, executions will be stopped in the first error. Nexx codes will not be executed.
        // You will not get any error messages about the second, third etc. errors.
        // That kind of assertions are called "Hard assertions"
        // In soft assertions(Verification) all codes will be executed and you will get report for all assertions

        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        // How to print status code, status line, content type etc. on the console
        //They are all in response container

        System.out.println("Status code"+response.getStatusCode());// We are using getters
        System.out.println("Content type"+response.getContentType());
        System.out.println("Status line"+response.statusLine());
        System.out.println("Execution Time is"+response.getTime());
        System.out.println("Headers are \n" +response.getHeaders()); // all headers
        System.out.println("Via is " +response.getHeader("Via")); // with the key will give the value of the header



    }


}

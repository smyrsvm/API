package practice;

import Base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class DummyGet03 extends DummyApiBaseUrl {

    /**
     When
        I send a GET request to the Url "http://dummy.restapiexample.com/api/v1/employees"
     Then
        HTTP Status Code should be 200
     And
        Content Type should be JSON
     And
        Status Line should be HTTP/1.1 200 OK
     And
        User can see foolowing  employees in the system
        Doris Wilder, Jenette Caldwell and Bradley Green

     */
    @Test
    public void test03 (){
        // 1) Set the Url
        spec.pathParams("firstparameter", "api","secondparameter","v1","thirdparameter","employees");

        // 2) Set the expected data
        // 3) Send request and get the data

        Response response = given().spec(spec).when().get("/{firstparameter}/{secondparameter}/{thirdparameter}");
        response.prettyPrint();

        // 4) Make assertions

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK").
                body("data.employee_name",hasItems("Doris Wilder","Jenette Caldwell","Bradley Green"));

    }


}

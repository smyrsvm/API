package practice;

import Base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class DummyGet06 extends DummyApiBaseUrl {

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
        3,7,11

     */

    @Test
    public void test06(){

        spec.pathParams("firstparasmeter","api","secondparameter", "v1","thirdparameter", "employees");

        Response response= given().spec(spec).when().get("/{firstparasmeter}/{secondparameter}/{thirdparameter}");

        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).body("data.id", hasItems(3,7,11));




    }












}

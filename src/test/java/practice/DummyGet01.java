package practice;

import Base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasSize;

public class DummyGet01 extends DummyApiBaseUrl {

    /**
     *
     * When
     *      I send a GET request to the Url "http://dummy.restapiexample.com/api/v1/employees"
     * Then
     *      HTTP Status Code should be 200
     * And
     *      Content Type should be JSON
     *And
     *      Status Line should be HTTP/1.1 200 OK
     *And
     *     There ara 24 employees in total

     */

    @Test
    public void test01 (){
        spec.pathParams("firstparameter", "api","secondparameter","v1","thirdparameter","employees");

        Response response =given().spec(spec).when().get("/{firstparameter}/{secondparameter}/{thirdparameter}");

        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK").
                body("data.id",hasSize(24));










    }






}

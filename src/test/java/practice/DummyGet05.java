package practice;

import Base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DummyGet05 extends DummyApiBaseUrl {

    /**
     *  Given
     *      "http://dummy.restapiexample.com/api/v1/employee"
     *  When
     *      User sends a request to the url
     *  Then
     *      HTTP Status Code should be 200
     *  And
     *     response content Type is "application/json"
     *  And
     *    this user exists in the system

     *      "data":
     *                 {
     *             "id": 6,
     *             "employee_name": "Brielle Williamson",
     *             "employee_salary": 372000,
     *             "employee_age": 61,
     *             "profile_image": ""
     *         }
     "message": "Successfully! All records has been fetched."
     *
     *    */


    @Test
    public void test05(){

        spec.pathParams("firstparameter", "api","secondparameter","v1","thirdparameter","employee","fourthparameter","6");

        Response response=given().spec(spec).when().get("/{firstparameter}/{secondparameter}/{thirdparameter}/{fourthparameter}");
        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data.id",equalTo(6),"data.employee_name",equalTo("Brielle Williamson"),
                        "data.employee_salary",equalTo(372000),"data.employee_age", equalTo(61),
                        "message",equalTo("Successfully! Record has been fetched."));


    }


}

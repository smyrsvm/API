package practice;

import Base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DummyGet04 extends DummyApiBaseUrl {
    /**
     *  Given
     *      "http://dummy.restapiexample.com/api/v1/employee"
     *  When
     *      User sends a get request to the url
     *  Then
     *      HTTP Status Code should be 200
     *  And
     *     response content Type is "application/json"
     *And
     *    this user exists in the system
     *
            "status": "success",
     *      "data":
     *                 {
     *             "id": 5,
     *             "employee_name": "Airi Satou",
     *             "employee_salary": 162700,
     *             "employee_age": 33,
     *             "profile_image": ""
     *         }
            "message": "Successfully! All records has been fetched."
     *
     *    */

    @Test
    public void test04(){

        spec.pathParams("firstparameter","api","secondparameter","v1","thirdparameter","employee","fourthparameter",5);

        Response response= given().spec(spec).when().get("/{firstparameter}/{secondparameter}/{thirdparameter}/{fourthparameter}");

        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("status", equalTo("success"),
                        "data.id",equalTo(5),
                        "data.employee_name",equalTo("Airi Satou"),
                        "data.employee_salary",equalTo(162700),
                        "data.employee_age",equalTo(33),
                        "message",equalTo("Successfully! Record has been fetched."));

    }

}

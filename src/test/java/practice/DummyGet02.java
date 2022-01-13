package practice;

import Base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class DummyGet02  extends DummyApiBaseUrl {


    /**
     When
          I send a GET request to the Url "http://dummy.restapiexample.com/api/v1/employees"
       Then
            HTTP Status Code should be 200
       And
            Content Type should be JSON
       And
            This user exists in the system;

     {
     "id": 3,
     "employee_name": "Ashton Cox",
     "employee_salary": 86000,
     "employee_age": 66,
     "profile_image": ""
     }

     */


    @Test
    public void test02(){

        // 1- Set the url

        spec.pathParams("firstparameter","api","secondparameter","v1", "thirdparameter","employees");

        // 2-Set the expected data
        // 3- Send request and get the data

        Response response= given().spec(spec).when().get("/{firstparameter}/{secondparameter}/{thirdparameter}");
        response.prettyPrint();

        //4- Make assertions

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data.id",hasItem(23),
                        "employee_name", hasItem("Caesar Vance"),"status",equalTo("success"),
                        "message", equalTo("Successfully! All records has been fetched."));


    }

}

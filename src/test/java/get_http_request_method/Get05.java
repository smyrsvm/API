package get_http_request_method;

import Base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {
    /*
     Given
            "https://restful-booker.herokuapp.com/booking"
     When
           User sends a request to the Url
     Then
          Status Code is 200
     And
        Among the data there should be someone whose first name is "Mark" and
        last name is "Ericsson"

     */

    @Test
    public void get05(){

        // 1st Step: Set the url

        // In pathparam; parameter name can be anything
        // In queryparam you cannot change key and values

        spec.
                pathParam("firstparameter","booking").
                queryParams("firstname","Mary","lastname","Ericsson");

        //2nd Step: Set the expected data

        //3rd Step: Send request, get response

        // We should just add the pathparam, we do not need to add something for queryparam

         Response response=  given().spec(spec).when().get("/{firstparameter}");
         response.prettyPrint();

         //4th: Make assertions

         response.then().assertThat().statusCode(200);

         assertTrue(response.asString().contains("bookingId"));













    }










}

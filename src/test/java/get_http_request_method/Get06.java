package get_http_request_method;

import Base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {

    /*

        Given
            https://restful-booker.herokuapp.com/booking/5 url
        When
            User sends a request to the url
        Then
            HTTP Status Code should be 200
        And
            response content Type is "application/json"

        And response body should be like;

                {"firstname": Sally,
                "lastname": "Smith",
                "totalprice": 789,
                "depositpaid": false,
                "bookingdates": {
                   "checkin": "2017-12-11",
                    "checkout":"2020-02-20"
                     }
                }
    */



    @Test
    public void get06(){

        // 1st Step: Set the url

        spec.pathParams("firstparameter", "booking", "secondparameter",5);

        // 2nd Step: Set the expected data
        // 3rd Step: Send request and get response

        Response response=given().spec(spec).when().get("/{firstparameter},/{secondparameter}");
        response.prettyPrint();

        //4th Step: Make assertions

        // First way:

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Mary"),
                        "lasrname", equalTo("Jackson"),
                        "totalprice", equalTo("333"),
                        "depositpaid", equalTo(false),
                        "bookingdates.checkin",equalTo("2017-12-11"),
                        "bookingdates.checkout", equalTo("2020-02-20"),
                        "addtionalneeds", equalTo("Breakfast"));

        // 2nd way: Use JsonPath
        // JsonPath is a class having many useful methods to navigate inside the Json Data

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

        // Create JsonPath object from response object

        JsonPath json= response.jsonPath();
        assertEquals("First name is not matching","Mary",json.getString("firstname"));
        assertEquals("Last name is not matching","Jones",json.getString("lastname"));
        assertEquals("Total price is not matching",812,json.getInt("totalprice"));
        assertEquals("Deposit paid is not matching","false",json.getBoolean("depositpaid"));
        assertEquals("Checkin date is not matching","2017-12-11",json.getString("bookingdates.checkin"));
        assertEquals("Checkout date is not matching","2020-02-20",json.getString("bookingdates.checkout"));

        // Instead of getString(), getInt()... methods we can use get( method). But using specific methods is better.

        // 3rd Way: Soft Assertion... It comes from TestNG

            // i) Create SoftAssert Object

            SoftAssert softAssert= new SoftAssert();

            // ii) By using softAssert object make assertions

            softAssert.assertEquals(json.getString("firstname"), "Sally", "First name is not mstching");
            softAssert.assertEquals(json.getString("lastname"), "Sally", "Last name is not mstching");
            softAssert.assertEquals(json.getInt("totalprice"), 544, "Total price is not matching");
            softAssert.assertEquals(json.getBoolean("depositpaid"), true, "Deposit paid is not mstching");

            // iii) Do not forget to use assertAll()
            // If you dont use assertAll() method, you will get "test passed" but actually it doesnt execute your codes
            softAssert.assertAll();



















    }








}

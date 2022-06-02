package get_http_request_method;
import Base_urls.HerOkuAppBaseUrl;
import io.cucumber.java.hu.Ha;
import io.restassured.response.Response;
import org.junit.Test;
import utils.JsonUtil;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class GetWithObjectMapper02 extends HerOkuAppBaseUrl {

/*
    Given
        https://restful-booker.herokuapp.com/booking/2
    When
        User sends a GET request to the url
    Then
        HTTP Status Code should be 200
    And
        response body should be like;

{
"firstname": "Sally",
"lastname": "Ericsson",
"totalprice": 211,
"depositpaid": true,
"bookingdates": {
    "checkin": "2020-10-21",
    "checkout": "2020-12-01"
},
"additionalneeds": "Breakfast"
}
*/


@Test
public void getWithObjectMapper02 (){

    //1st Step: Set the url

    spec.pathParams("firstparameter","booking","secondparameter","2");

    //2nd Step: Set the expected data
    //1st Way:


    String expectedData= "{\n" +
            "\"firstname\": \"Sally\",\n" +
            "\"lastname\": \"Ericsson\",\n" +
            "\"totalprice\": 211,\n" +
            "\"depositpaid\": true,\n" +
            "\"bookingdates\": {\n" +
            "    \"checkin\": \"2020-10-21\",\n" +
            "    \"checkout\": \"2020-12-01\"\n" +
            "},\n" +
            "\"additionalneeds\": \"Breakfast\"\n" +
            "}";

        //2nd Way: Create a SetUp method to convert Json Data to String dynamically

        HashMap<String,Object> expectedDataMap=  JsonUtil.convertJsonToJava(expectedData, HashMap.class);
        System.out.println(expectedDataMap);

        // 3rd Step: Send request and get data

        Response response= given().spec(spec).when().get("/{firstparameter}/{secondparameter}");
        response.prettyPrint();

        HashMap<String,Object> actualDataMap=JsonUtil.convertJsonToJava(response.asString(), HashMap.class);

        // 4th Step: Do the assertions
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin") ,((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"),((Map) actualDataMap.get("bookingdates")).get("checkout"));

    }

}








































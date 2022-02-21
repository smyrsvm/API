package get_http_request_method;

import Base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get09 extends HerOkuAppBaseUrl {

    /*
     Given
            "https://restful-booker.herokuapp.com/booking/1"
     When
           User sends a request to the Url
     Then
          Response body should be like that;

                {"firstname": Eric,
                "lastname": "Smith",
                "totalprice": 555,
                "depositpaid": false,
                "bookingdates": {
                   "checkin": "2016-09-09",
                    "checkout":"2017-09-21"
                     }
                }

     */

    @Test
    public void Get09 (){

        // 1st Step: Set the url

        spec.pathParams("first","booking","second",1);

        // 2nd Step: Set the expected data

        Map<String, String> expectedBookingDates= new HashMap<>();
        expectedBookingDates.put("checkin","2021-12-30");
        expectedBookingDates.put("checkout","2022-01-04");


        Map<String,Object> expectedData= new HashMap<>();
        expectedData.put("firstname","Mark");
        expectedData.put("lastname","Smith");
        expectedData.put("totalprice",472);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",expectedBookingDates);
        expectedData.put("additionalneeds","additionalneeds");
        System.out.println(expectedData);

        //3rd: send request and get response

        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map<String,Object> actualData= response.as(HashMap.class);// De-serialization; converting Json data to JAva(Object) through GSON
        System.out.println(actualData);

        // 4th: Make assertions

        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));


        /** Our actualData as Map is below. In our Map keys` data type are String, values` data type are Object */

        // Map<String,Object> actualData= response.as(HashMap.class)

        // {firstname=Jim, additionalneeds=Breakfast, bookingdates={checkin=2019-09-29, checkout=2020-05-08},
        // totalprice=391, depositpaid=false, lastname=Smith}

        // Bookingdates` value seems to have a variable like Map data type.
        // But indeed it is an Object not a Map, because we set it like it before.

        // So we have to convert this Object to a map by Explicit Type Casting below.

        //actualData.get("bookingdates")--> it is Object. To be able to use get() method, i have to
        // convert it to Map.Because this method comes with Map.

        assertEquals(expectedBookingDates.get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(expectedBookingDates.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));


    }


}

package post_http_request_method;

import Base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.xmlbeans.impl.jam.mutable.MAnnotatedElement;
import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Post01 extends HerOkuAppBaseUrl {

    /*
     Given
            "https://restful-booker.herokuapp.com/booking"

                {"firstname": Selim,
                "lastname": "Ak",
                "totalprice": 111111,
                "depositpaid": true,
                "bookingdates": {
                   "checkin": "2016-09-09",
                    "checkout":"2017-09-21"
                     }
                }

       When
           User sends POST request to the Url
        Then
            Status code is 200
        And
          Response body should be like that;
          {
                "bookingid":11,
                "booking": {"firstname": Eric,
                "lastname": "Smith",
                "totalprice": 555,
                "depositpaid": false,
                "bookingdates": {
                   "checkin": "2016-09-09",
                    "checkout":"2017-09-21"
                     }
                }
         }
     */

    @Test
    public void post01() {

        // 1st Step: Set the Url

        spec.pathParam("firstparameter","booking");

        // 2nd: Set the expected data

        Map<String,String> expectedBookingDates= new HashMap<>();
        expectedBookingDates.put("checkin","2016-09-09");
        expectedBookingDates.put("checkout","2017-09-21");

        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("firstname","Selim");
        expectedData.put("lastname","Ak");
        expectedData.put("totalprice",111111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",expectedBookingDates);
        System.out.println(expectedData);

        // 3rd: Send request and get response

        // When posting request, some APIs want also the contentType, like API of HerOkuApp.
        // It depends on the API structure. These are all written in Swagger Documentations.

        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{firstparameter}");
        response.prettyPrint();

        // 4th: Make assertions

        Map<String,Object>actualData= response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(expectedData.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"), ((Map) actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), ((Map) actualData.get("booking")).get("depositpaid"));
        assertEquals(expectedBookingDates.get("checkin"), ((Map)((Map) actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(expectedBookingDates.get("checkout"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));


    }






















}

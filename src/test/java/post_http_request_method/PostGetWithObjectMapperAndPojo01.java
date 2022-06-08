package post_http_request_method;

import Base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingPostResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostGetWithObjectMapperAndPojo01 extends HerOkuAppBaseUrl {

    /*
     Given
            "https://restful-booker.herokuapp.com/booking"

                {"firstname": "Selim",
                "lastname": "Ak",
                "totalprice": 111111,
                "depositpaid": true,
                "bookingdates": {
                   "checkin": "2016-09-09",
                    "checkout":"2017-09-21"
                     },
                 "additionalneeds": "Breakfast"
                }
       When
           User sends POST request to the Url
       And
           User sends GET Request to the Url
       Then
           Status code is 200
       And
           GET Response body should be like that;
           {
                "firstname": Selim,
                "lastname": "Ak",
                "totalprice": 111111,
                "depositpaid": true,
                "bookingdates": {
                   "checkin": "2016-09-09",
                    "checkout":"2017-09-21"
                     },
                  "additionalneeds": "Breakfast"

              }

     */

    @Test
    public void PostGetWithObjectMapperAndPojo01 (){
                        //SENDING POST REQUEST 1st Step, 2nd Step, 3rd Step
        //1st Step: Set the URL

        spec.pathParam("first","booking");

        //2nd Step: Set the request body

        BookingDatesPojo bookingDates= new BookingDatesPojo("2016-09-09","2017-09-21");
        BookingPojo requestBody= new BookingPojo("Selim","Ak",11111,true,bookingDates,"Breakfast");
        System.out.println(requestBody);

        //3rd Step: Send the request and get the response

        Response response= given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();

                    // CONVERT POST RESPONSE BODY TO JAVA OBJECT BY USING OBJECT MAPPER

        BookingPostResponseBodyPojo postResponseBodyInPojo= JsonUtil.convertJsonToJava(response.asString(), BookingPostResponseBodyPojo.class);
        System.out.println(postResponseBodyInPojo);

        //We need now bookingID to read the data.We will use GET request
        // Previously we used JsonPath to get the bookingID.
        // Now our response is in POJO. And in POJO I have getter methods_ getBookingid

                            // FROM postResponseBodyInPojo WE GOT THE VALUE OF bookingId BY USING GETTER OF bookingId
        Integer bookingId= postResponseBodyInPojo.getBookingid();

                            // BY USING bookingId I WILL SEND GET REQUEST IN 1.step, 2. Step, 3. Step
        //1st step: Set the URL  for Get Request

        spec.pathParams("first","booking","second",bookingId);

        //2nd step: Set the expected Data
        // No need to create expected data because the data which is sent in the POST Request will be expected data

        // 3rd Step: Send the request and get the response
        Response response2= given().spec(spec).when().get("/{first/{second}");
        response2.prettyPrint();

                            // I CONVERT GET RESPONSE BODY TO JAVA OBJECT BY USING OBJECT MAPPER
        BookingPojo getResponseBodyInPojo = JsonUtil.convertJsonToJava(response2.asString(),BookingPojo.class);

                            // BY USING requestBody and getResponseBodyInPojo, I DID ASSERTIONS

        //4th. Step: Do assertions

        assertEquals(200,response2.getStatusCode());
        assertEquals(requestBody.getFirstname(),getResponseBodyInPojo.getFirstname());
        assertEquals(requestBody.getLastname(),getResponseBodyInPojo.getLastname());
        assertEquals(requestBody.getDepositpaid(),getResponseBodyInPojo.getDepositpaid());
        assertEquals(requestBody.getBookingdates().getCheckin(),getResponseBodyInPojo.getBookingdates().getCheckin());
        assertEquals(requestBody.getBookingdates().getCheckout(),getResponseBodyInPojo.getBookingdates().getCheckout());
        assertEquals(requestBody.getAdditionalneeds(),getResponseBodyInPojo.getAdditionalneeds());
    }

}

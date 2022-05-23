package post_http_request_method;

import Base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingPostResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;


public class PostWithPojo02 extends HerOkuAppBaseUrl {

    /*

    Given
        https://restful-booker.herokuapp.com/booking

                            {"firstname": Suleyman,
                            "lastname": "Alptekin",
                            "totalprice": 999,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2016-02-05",
                                "checkout":"2021-01-16"
                    }
                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                    }

    When
        User sends a POST request to the url
        User sends GET request to the url to get the newly created data
    Then
        HTTP Status Code should be 200
    And
        response body should be like;

                    {"firstname": Mary,
                            "lastname": "Smith",
                            "totalprice": 647,
                            "depositpaid": false,
                            "bookingdates": {
                                "checkin": "2016-02-05",
                                "checkout":"2021-01-16"
                    }
                        "additionalneeds": "Breakfast"
                    }

    */


    @Test
    public void postWithPojo02 (){

        // If the response structure is different from request structure
        // 1. First way; creating new Pojo for response. we did it in this test method
        // 2. This second way, we did in below method numbered as 3.
        // It is --> Using GET request if we are sure that Get response data structure is
        // same with the post request data structure. How could we be sure of that? --> By Postman
        // But to be able to use GET request, I need bookingid. How could I get it?--> By using JsonPath
        // I get it with JsonPath and use in GET request.

        // 1st Step: Set the url

        spec.pathParam("first","booking");
        spec.pathParam("first","booking");

        // 2nd Step: Set request body

        BookingDatesPojo bookingDates= new BookingDatesPojo("2016-02-05","2021-01-16");
        BookingPojo requestBody= new BookingPojo("Suleyman","Alptekin",999,true,bookingDates,"Breakfast with white tea, Dragon juice");
        System.out.println(requestBody);

        // 3rd Step; Send request and get response

        // After given() to when() --> spec, contentType, body are => prerequisities,
        // With when() starts action :)

        Response response= given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();

        //After you get the response; check how it comes. For example; here, a new json came with outer nest that made my response structure different
        // from the pojo class format i created.

        // 4th Step; Do the assertions

        //Convert response body to Pojo by using GSON
        BookingPostResponseBodyPojo actualData= response.as(BookingPostResponseBodyPojo.class);
        System.out.println(actualData);
        
        assertEquals(200,response.getStatusCode());
        assertEquals(requestBody.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(requestBody.getLastname(),actualData.getBooking().getLastname());
        assertEquals(requestBody.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(requestBody.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(requestBody.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(requestBody.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(requestBody.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());
    }


    @Test
    public void postWithPojo03 (){

        // We used two requests in this test method. We created data and read it.
        // There is no need to create a new POJO here.

        // 1st Step: Set the url

        spec.pathParam("first","booking");

        // 2nd Step: Set request body

        BookingDatesPojo bookingDates= new BookingDatesPojo("2016-02-05","2021-01-16");
        BookingPojo requestBody= new BookingPojo("Suleyman","Alptekin",999,true,bookingDates,"Breakfast with white tea, Dragon juice");
        System.out.println(requestBody);

        // 3rd Step: Send request and get response

        Response response= given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();

        // 4th Step: Do the assertions

        //Note: After creating new data in Database, you will need "bookingid" to be able to use GET Method
        // So you need to get "bookingid" from the response of post request. We get it with JsonPath.
        // JsonPath is good when we need a specific data from response.

        JsonPath json= response.jsonPath();
        Integer bookingId=json.getInt("bookingid");
        System.out.println(bookingId);

        // Set the url for GET Request
        // For get request we need two parameters

        spec.pathParams("first","booking","second",bookingId);
        //This bookingid is updated everytime so i did not put a fixed number

        // Send the GET Request and get response

        Response response2=given().spec(spec).when().get("/{first}/{second}");
        response2.prettyPrint();

        // Do the assertion

        // We use here GET response body
        // Convert response2 to POJO. GET response format is BookingPojo format

        BookingPojo actualData = response2.as(BookingPojo.class);

        assertEquals(200,response2.getStatusCode());
        assertEquals(requestBody.getFirstname(),actualData.getFirstname());
        assertEquals(requestBody.getLastname(),actualData.getLastname());
        assertEquals(requestBody.getTotalprice(),actualData.getTotalprice());
        assertEquals(requestBody.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(requestBody.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(requestBody.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(requestBody.getAdditionalneeds(),actualData.getAdditionalneeds());
    }

    @Test
    public void postWithPojo04 (){
        // Although this test was deleted since the API did not let us to delete any data, i did not delete.

        // We used three requests in this test method.
        // We created data then deleted it and then read it.

        // 1st Step: Set the url

        spec.pathParam("first","booking");

        // 2nd Step: Set request body

        BookingDatesPojo bookingDates= new BookingDatesPojo("2016-02-05","2021-01-16");
        BookingPojo requestBody= new BookingPojo("Suleyman","Alptekin",999,true,bookingDates,"Breakfast with white tea, Dragon juice");
        System.out.println(requestBody);

        // 3rd Step: Send request and get response

        Response response= given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();

        JsonPath json= response.jsonPath();
        Integer bookingId=json.getInt("bookingid");
        System.out.println(bookingId);

        // Set the url for Delete Request

        spec.pathParams("first","booking","second",bookingId);
        //This bookingid is updated everytime so i did not put a fixed number

        // Send the Delete Request and get response

        Response response2=given().spec(spec).when().delete("/{first}/{second}");
        response2.prettyPrint();

        // Set the url for GET Request
        spec.pathParams("first","booking","second",bookingId);

        // Send the GET Request
        Response response3=given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
        response3.prettyPrint();
    }

}

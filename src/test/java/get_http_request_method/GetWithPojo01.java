package get_http_request_method;

import Base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class GetWithPojo01 extends HerOkuAppBaseUrl {


    /*
        Given
            https://restful-booker.herokuapp.com/booking/2
        When
            User sends a request to the url
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
    public void GetWithPojo01 (){

        //1st Step: Set the url
        spec.pathParams("firstparameter","booking","secondparameter",2);

        //2nd Step: Set the expected data

        BookingDatesPojo bookingDates = new BookingDatesPojo("2021-08-10","2021-12-19");
        BookingPojo expectedData= new BookingPojo("Mark","Jones",278,true,bookingDates,"Breakfast");
        System.out.println(expectedData); //By toString() method we can see the booking object, without it we could just see the reference number

        //3rd Step: Send request and get response

        Response response=given().spec(spec).when().get("/{firstparameter}/{secondparameter}");
        response.prettyPrint();

        //4th Step: Do assertions

        BookingPojo actualData=response.as(BookingPojo.class);
        System.out.println(actualData);


        assertEquals(200,response.getStatusCode());
        assertEquals("First Names are not matching",expectedData.getFirstname(),actualData.getFirstname());
        assertEquals("Last Names are not matching",expectedData.getLastname(),actualData.getLastname());
        assertEquals("Total Prices are not matching",expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals("Deposit paid is not matching",expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals("Additional needs are not matching",expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());
        assertEquals("Check in dates are not matching",expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals("Check out dates are not matching",expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());


    }

    // While you are working, dont use sout, prettyPrint and blanks.Push like below
    @Test
    public void GetWithPojo02 (){
        spec.pathParams("firstparameter","booking","secondparameter",2);
        BookingDatesPojo bookingDates = new BookingDatesPojo("2016-02-05","2021-01-16");
        BookingPojo expectedData= new BookingPojo("Mary","Smith",647,false,bookingDates,"Breakfast");
        Response response=given().spec(spec).when().get("/{firstparameter}/{secondparameter}");
        BookingPojo actualData=response.as(BookingPojo.class);
        assertEquals(200,response.getStatusCode());
        assertEquals("First Names are not matching",expectedData.getFirstname(),actualData.getFirstname());
        assertEquals("Last Names are not matching",expectedData.getLastname(),actualData.getLastname());
        assertEquals("Total Prices are not matching",expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals("Deposit paid is not matching",expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals("Additional needs are not matching",expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());
        assertEquals("Check in dates are not matching",expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals("Check out dates are not matching",expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
    }

}

package get_http_request_method;

import Base_urls.GoRestApiBAseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestApiBAseUrl {

    /*

        Given
               https://gorest.co.in/public/v1/users/13

       When
            User sends GET Request to the URL
       Then
        Status Code should be 200

       And
        Response body should be like

        {
            "meta": null,
            "data": {
                "id": 13,
                "name": "Giriraaj Gowda",
                "email": "giriraaj_gowda@hackett.io",
                "gender": "female",
                "status": "active"
    }
}
 */

@Test
public void get10(){

    //1st Step: Set the URL
    spec.pathParams("firstparameter","users","secondparameter",13);

    //2nd Step:Set the expected data

    GoRestDataPojo dataPojo= new GoRestDataPojo("Giriraaj Gowda","giriraaj_gowda@hackett.io","female","active");

    GoRestPojo expectedDataPojo= new GoRestPojo(null, dataPojo);

    //System.out.println(expectedDataPojo); Before pushing your codes to GitHub, delete all sout

    //3rd Step: Send the request and get the response

    Response response= given().spec(spec).when().get("/{firstparameter}/{secondparameter}");

    GoRestPojo actualDataPojo= JsonUtil.convertJsonToJava(response.asString(),GoRestPojo.class);

    //4th. Step: Do assertions

    //assertEquals(expectedDataPojo,actualDataPojo); // They are completely in the same order.It failed.We normally do not do general assertions
    assertEquals(200,response.getStatusCode());
    assertEquals(expectedDataPojo.getMeta(),actualDataPojo.getMeta());
    assertEquals(expectedDataPojo.getData().getEmail(),actualDataPojo.getData().getEmail());
    assertEquals(expectedDataPojo.getData().getGender(),actualDataPojo.getData().getGender());
    assertEquals(expectedDataPojo.getData().getName(),actualDataPojo.getData().getName());
    assertEquals(expectedDataPojo.getData().getStatus(),actualDataPojo.getData().getStatus());

}

}

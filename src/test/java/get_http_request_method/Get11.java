package get_http_request_method;

import Base_urls.GoRestApiBAseUrl;
import io.cucumber.java.en.Given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class Get11 extends GoRestApiBAseUrl {

    /* Given
            https://gorest.co.in/public/v1/users/13
       When
            User sends GET Request
       Then
            The value of "pagination limit" is 20
       And
           The current link  "https://gorest.co.in/public/v1/users?page=1"
       And
            The number of users should be 31
       And
            The number of "active" users should be 9
       And
            "Pramila Guha", "Rudra Iyer" "Chinmayanand Iyer" are among the users
       And
            Female users are less than male users

     */


    @Test
    public void get11(){

        //1.Step:  Set the URL
        spec.pathParam("first","users");

        //2nd Step: Set the expected data
        // Here it is completed to set all the expected data in Pojo
        // JsonPath is the best way to use

        //3rd step: Send the GET Request and get the response

       Response response= given().spec(spec).when().get("/{first}");
       response.prettyPrint();

        //4th Step: Do assertions

        response.then().assertThat().statusCode(200).body("meta.pagination.limit",equalTo(20),
              "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data.id",hasSize(20));

        JsonPath json= response.jsonPath();










    }












}

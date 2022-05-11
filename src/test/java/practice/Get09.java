package practice;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItems;

public class Get09 extends JsonPlaceHolderBaseUrl {

    // Given
    //      "https://jsonplaceholder.typicode.com/comments"
    // When
    //     I send a GET request to the Url
    // Then
    //     HTTP Status Code should be 200
    // And
    //     User can see following emails in the system
    //     Zola@lizzie.com, Dolly@mandy.co.uk and Davidon@eldora.net


    @Test
    public void get09 (){

        spec.pathParam("firstparameter","comments");

        Response response=given().spec(spec).when().get("/{firstparameter}");
        response.prettyPrint();

        response.then().assertThat().
                                    statusCode(200).
                                    body("email",hasItems("Devan.Nader@ettie.me","Ronny@rosina.org","Jayne_Kuhic@sydney.com"));


    }


}

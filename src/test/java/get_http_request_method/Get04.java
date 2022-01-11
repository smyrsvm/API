package get_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonPlaceHolderBaseUrl {

    /**
     * Given
     *      "https://jsonplaceholder.typicode.com/todos"
     * When
     *      I send a GET request to the Url
     *  And
     *      Accept type is "application/json"
     *  Then
     *      HTTP Status Code should be 200
     *  And
     *      Response format should be "application/json"
     *  And
     *      there should be 200 todos
     *  And
     *      "quis eius es sint explicabo" should be one of the todos
     *  And
            2, 7, and 9 should be among the userIds

     */

    @Test
    public void get04(){

        //1.Step: Set the url

        spec.pathParam("firstparameter","todos");

        // 2nd Step: Set the expected data

        // 3rd: Send request get response

        // When accept type is not a prerequisite, we did like--> given().spec(spec).when().get("/{first}/{second}");

       Response response= given().spec(spec).accept(ContentType.JSON).when().get("/{firstparameter}");
       response.prettyPrint();

       //4th Step: Make the assertions

        // When we write for example "title", java puts all the titles to a list and with hasItem or hasSize methods
        // we can make searches in this list.

        // "contains" is for Strings
        // "hasItem" for lists

        //  Inside the  body method, we can use these most common methods; equalTo, hasSize, hasItem, hasItems methods


        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id",hasSize(200)).
                body("title",hasItem("quis eius est sint explicabo")).
                body("userId",hasItems(2,7,9));


    }


}

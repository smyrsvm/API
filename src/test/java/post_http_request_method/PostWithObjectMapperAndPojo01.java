package post_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class PostWithObjectMapperAndPojo01 extends JsonPlaceHolderBaseUrl {

     /*
         Given
                "https://jsonplaceholder.typicode.com/todos"

                    {"userId": 55,
                    "title": "Tidy your room",
                    "completed": false,
                    }

           When
               User sends POST request to the Url
            Then
                Status code is 201
            And
                Response body should be like that;
                    {
                    "userId": 55,
                    "title": "Tidy your room",
                    "completed": false,
                    "id ":201
                    }
     */

    @Test
    public void postWithObjectMapperAndPojo01 () {

        // 1st Step: Set the url

        spec.pathParam("first","todos");

        // 2nd Step: Set the requestBody

        JsonPlaceHolderTestData expected= new JsonPlaceHolderTestData();
        String expectedData= expected.expectedDataInString(55,"Tidy your room",false);

        JsonPlaceHolderPojo expectedDataPojo=JsonUtil.convertJsonToJava(expectedData, JsonPlaceHolderPojo.class);
        System.out.println(expectedDataPojo);

        //3rd Step: Send the request and get the response

        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedDataPojo).when().post("/{first}");
        response.prettyPrint();

         JsonPlaceHolderPojo actualDataPojo=JsonUtil.convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);
         System.out.println(actualDataPojo);

         //4th Step: Do assertions
        assertEquals(201,response.getStatusCode());
        assertEquals(expectedDataPojo.getUserID(),actualDataPojo.getUserID());
        assertEquals(expectedDataPojo.getTitle(),actualDataPojo.getTitle());
        assertEquals(expectedDataPojo.getCompleted(),actualDataPojo.getCompleted());

    }
}



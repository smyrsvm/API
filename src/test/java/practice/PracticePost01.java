package practice;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Comment;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class PracticePost01 extends JsonPlaceHolderBaseUrl {

     /*
        Given
                https://jsonplaceholder.typicode.com/comments

                {
                "name": "This class has smart people";
                "postId": 85,
                "id": 501,
                "body": "Congratulations Everyone",
                "email": "techproedstudents@gmail.com"
                }

       When
            User sends Post Request to the URL

       Then
            The Status Code should be 201

            Response should be like

                 {
                "name": "This class has smart people";
                "postId": 85,
                "id": 501,
                "body": "Congratulations Everyone",
                "email": "techproedstudents@gmail.com"
                }


     */

    @Test
    public void practicePost01WithPojo (){

        spec.pathParam("firstparameter", "comments");

        Comment requestBody= new Comment(85,501,"This class has smart people","techproedstudents@gmail.com","Congratulations Everyone");

        System.out.println(requestBody);

        Response response=given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{firstparameter}");

        response.prettyPrint();

        Comment actualData=response.as(Comment.class);
        System.out.println(actualData);

        assertEquals(requestBody.getPostId(),actualData.getPostId());
        assertEquals(requestBody.getId(),actualData.getId());
        assertEquals(requestBody.getName(),actualData.getName());
        assertEquals(requestBody.getEmail(),actualData.getEmail());
        assertEquals(requestBody.getBody(),actualData.getBody());

    }


    @Test
    public void practicePost01RequestWithMap (){

        spec.pathParam("firstparameter", "comments");

        Map<String,Object> expectedDataMap= new HashMap<>();
        expectedDataMap.put("name","This class has smart people");
        expectedDataMap.put("postId",85);
        expectedDataMap.put("id",501);
        expectedDataMap.put("body","Congratulations Everyone");
        expectedDataMap.put("email","techproedstudents@gmail.com");

        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{firstparameter}");
        response.prettyPrint();

        response.then().assertThat().statusCode(201);

        Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(expectedDataMap,actualData);
        assertEquals(201,response.getStatusCode());
        assertEquals(expectedDataMap.get("postId"),actualData.get("postId"));
        assertEquals(expectedDataMap.get("name"),actualData.get("name"));
        assertEquals(expectedDataMap.get("id"),actualData.get("id"));
        assertEquals(expectedDataMap.get("body"),actualData.get("body"));
        assertEquals(expectedDataMap.get("email"),actualData.get("email"));

    }

}

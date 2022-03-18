package practice;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Comment;

import javax.swing.plaf.IconUIResource;
import java.util.List;

import static groovyjarjarantlr4.v4.runtime.tree.xpath.XPath.findAll;
import static io.restassured.RestAssured.given;

public class Get08 extends JsonPlaceHolderBaseUrl {


       /*
        Given
                https://jsonplaceholder.typicode.com/comments
        When
            User sends GET request to the URL
        Then
            HTTP Status Code should be 200
         And
           Search all ids are less than 30
           the number of ids less than 30 should be 29

     */


    @Test
    public void get08 (){

        //1st Step: Set the url
        spec.pathParam("firstparameter","comments");

        // 2nd Step: Set the expected data
        // 3rd Step: Send request and get response

        Response response= given().spec(spec).when().get("/{firstparameter}");
        response.prettyPrint();

        // 4th Step: Do assertions
        response.then().assertThat().statusCode(200);

        JsonPath json= response.jsonPath();
        List<Integer>idList =json.getList("findAll{it.id<30}.id");
        System.out.println(idList);

        Assert.assertEquals("idList`s size is not 29",29,idList.size());
        Assert.assertTrue("idList`s size is not 29",idList.size()==29);


    }

    /*
        Given
                https://jsonplaceholder.typicode.com/comments
        When
            User sends GET request to the URL
        Then
            HTTP Status Code should be 200
         And
           Search all ids are less than 30
           the number of ids less than 30 should be 29

           Use just pojo classes for this validation

     */

    @Test
    public void pojoGet08 (){

        spec.pathParam("firstparameter","comments");

        Response response= given().spec(spec).when().get("/{firstparameter}");
        //response.prettyPrint();

        Comment [] comment= response.as(Comment[].class);

           int count=0;
           for (int i=0; i<comment.length;i++) {
               if (comment[i].getId()<30) {
                   count++;
               }
               System.out.println(i+ "id "+ comment[i].getId());

           }

       Assert.assertTrue(count==29);


    }

}

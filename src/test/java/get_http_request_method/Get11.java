package get_http_request_method;

import Base_urls.GoRestApiBAseUrl;
import io.cucumber.java.en.Given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
            we have at least one "active" status
       And
            "Pramila Guha", "Rudra Iyer" "Chinmayanand Iyer" are among the users
       And
            Female users are more than male users
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

        //1st way:

        response.then().assertThat().statusCode(200).body("meta.pagination.limit",equalTo(20),
              "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data.id",hasSize(20),"data.status",hasItem("active"),
                "data.name", hasItems("Pramila Guha", "Rudra Iyer", "Chinmayanand Iyer"));

        //2nd way:Use JsonPath to assert
        JsonPath json= response.jsonPath();

        response.then().assertThat().statusCode(200);

        assertEquals("Pagination limit is not matching",20,json.getInt("meta.pagination.limit"));
        assertEquals("https://gorest.co.in/public/v1/users?page=1", json.getString("meta.pagination.links.current"));
        assertEquals(20,json.getList("data.id").size());
        assertTrue(json.getList("data.status").contains("active"));
        //assertTrue(json.getList("data.name").containsAll("Pramila Guha", "Rudra Iyer", "Chinmayanand Iyer"));
        //This did not work because containsAll() method works with Collections. Above we wrote String, so it did not work.
        //So we made like below...

        List<String> expectedNamesList = Arrays.asList("Pramila Guha", "Rudra Iyer", "Chinmayanand Iyer");
        assertTrue(json.getList("data.name").containsAll(expectedNamesList));
        // This is better to add names again and again.If you have a list you can create like it.it is short.
        // if you do not change size you can use it as a List
        // But after creating list, you want to change the size do not create with asList because
        // it does not let you to use the methods which changes the size of the list
        // We used here Arrays.asList because we do not want to change the size


        //List<Integer> idList=  json.getList("findAll{it.id>190}.id"); another type

        // The number of females are greater than the number of males
        // We did not use Groovy language, so it is long
        List<String> genderList= json.getList("data.gender");
        System.out.println(genderList);

        int femaleCounter = 0;
        for (String w : genderList) {

            if (w.equals("female")){
                femaleCounter++;
            }
        }

        assertTrue(femaleCounter>genderList.size()-femaleCounter);

        // The number of active status is more than 5
        // First Way: Use loop

        List<String> statusList= json.getList("data.status");
        System.out.println(statusList);

        int statusCounter=0;
        for (String w: statusList) {

            if (w.equals("active")){
                statusCounter++;
            }
        }

        assertTrue(statusCounter>5);

        //2nd Way: Use Groovy
        List<String> listOfActiveStatus = json.getList("data.findAll{it.status=\"active\"}");
        System.out.println(listOfActiveStatus);

        assertTrue(listOfActiveStatus.size()>5);

    }


}

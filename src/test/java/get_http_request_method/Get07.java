package get_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get07 extends JsonPlaceHolderBaseUrl {

    /*
        Given
                https://jsonplaceholder.typicode.com/todos
        When
            User sends GET request to the URL
        Then
            1) Status Code is 200
            2) Print all ids greater than 190 on the console
                Assert that there are 10 ids greater than 190
            3) Print all userIds less than 5 on the console
                assert that maximum userId less than 5 is 4
            4) Print all titles whose ids are less than 5
                Assert that "delectus aut autem" is one of the titles whose id is less than 5

     */

    @Test
    public void get07(){

        //1-Set the url

        spec.pathParam("first", "todos");

        // 2.Set the expected data
        // 3.Send request and get response

        Response response= given().spec(spec).when().get("/{first)");

        //response.prettyPrint();

        // 4.Make assertions

        response.then().assertThat().statusCode(200);

        /** 2) Print all ids greater than 190 on the console */

        JsonPath json= response.jsonPath();
        List<Integer> idList=  json.getList("findAll{it.id>190}.id");// groovy Language, works under java

        // it.id --> like---> this.name
        // this--> means from the class which you are in, get the name variable
        // constructor this has paranthesis.
        //???? it.id--> from the Json Data which we are working in, look at the ids.If ids are greater than 190 take the data
        // In Java it is used "this", in Groocy it is used "it".

        System.out.println(idList); // [191,192,193....200]

        /** Assert that there are 10 ids greater than 190 */

        assertEquals("idlist doesnt have expected size",10,idList.size());

        /** 3) Print all userIds less than 5 on the console
         assert that maximum userId less than 5 is 4 */

         List<Integer>userIdList= json.getList("findAll{it.userId<5}.userId");
         System.out.println(userIdList);
         Collections.sort(userIdList);// Elements are sorted in ascending order

        //assertEquals("",4,userIdList.get(userIdList.size()-1));
        // It is wrong when we write it like above. Because assertEquals works with Object, Object
        //Actual value is Integer, and expected value of 4 is accepted as Object; so it complains.
        // Then we should convert that to Integer.Explicit casting we are making

        assertEquals("",(Integer)4,userIdList.get(userIdList.size()-1));

        /** 4) Print all titles whose ids are less than 5 */

        List<String> titlesList=json.getList("findAll{it.id<5}.title");
        System.out.println(titlesList);

        /** Assert that "delectus aut autem" is one of the titles whose id is less than 5 */

        // 1st way:
        assertTrue(titlesList.contains("delectus aut autem"));

        //2. Way: LAmbda is used with Collections
        assertTrue(titlesList.stream().anyMatch(t->t.equals("delectus aut autem")));  ;

    }



}

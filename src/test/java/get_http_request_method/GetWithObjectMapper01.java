package get_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.cucumber.java.hu.Ha;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class GetWithObjectMapper01 extends JsonPlaceHolderBaseUrl {

    /*
        Given
            "https://jsonplaceholder.typicode.com/todos"
        When
           I send Get request to the Url
        Then
            Status code is 200
        And
            Response body should be like that;
                {
                "userId": 10,
                "title": "quis eius est sint explicabo",
                "completed": false,
                "id ":198
                }
     */

    @Test
    public void GetWithObjectMapper01() {

            //1St step: Set the url
            spec.pathParams("firstparameter","todos","secondparameter",198);

            //2nd step: Set the expected data

                // 1st Way: Not recommended

                /** String expecteddata="{\n" +
                        "\"userId\": 10,\n" +
                        "\"title\": \"quis eius est sint explicabo\",\n" +
                        "\"completed\": false,\n" +
                        "\"id \":198\n" +
                        "}";
                HashMap<String,Object>  expectedDataMap=JsonUtil.convertJsonToJava(expectedData, HashMap.class);
                 */

            // We put our json data in double quotes. The thing which you put in double quotes is String.
            // We use String because my objectMapper method(convertJsonToJava) uses String.

                // 2nd Way:

            JsonPlaceHolderTestData expected= new JsonPlaceHolderTestData();
            String expectedData=expected.expectedDataInString(10,"quis eius est sint explicabo",true);

            // My method uses 2 parameters.First one is json second is Java Object
            HashMap<String,Object>  expectedDataMap= JsonUtil.convertJsonToJava(expectedData, HashMap.class);

            //3rd Step: Send request and get response

             Response response= given().spec(spec).when().get("/{firstparameter}/{secondparameter}");
             response.prettyPrint();

             HashMap<String,Object> actualDataMap=JsonUtil.convertJsonToJava(response.asString(),HashMap.class);
             System.out.println(actualDataMap);

             //4th Step: Do the assertions

            assertEquals(200,response.getStatusCode());
            assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
            assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
            assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));

    }

}

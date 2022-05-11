package practice;

import Base_urls.DummyApiBaseUrl;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class DummyGet07 extends DummyApiBaseUrl {
    /**
     *  Given
     *      "http://dummy.restapiexample.com/api/v1/employee"
     *  When
     *      User sends a GET request to the url
     *  Then
     *      HTTP Status Code should be 200
     *  And
     *     response content Type is "application/json"
     *And
         Make sure Rhona Davidson earns more than Herrod Chandler

     *                 {
     *             "id": ,7
     *             "employee_name": "Herrod Chandler",
     *             "employee_salary": 137500,
     *             "employee_age": 59,
     *             "profile_image": ""
     *         },
     *
     *          {
     *      *             "id": 8
     *      *             "employee_name": "Rhona Davidson",
     *      *             "employee_salary": 327900,
     *      *             "employee_age": 55,
     *      *             "profile_image": ""
     *      *         }

     *    */

    @Test
    public void get07 (){

        // This is another way.We get all the employees data.
        // This is not a preferred way.
        //Response response = RestAssured.get("http://dummy.restapiexample.com/api/v1/employees");

        spec.pathParams("firstparameter","api","secondparameter","v1","thirdparameter","employee","fourthparameter",7);

        Response response1= given().spec(spec).when().get("/{firstparameter}/{secondparameter}/{thirdparameter}/{fourthparameter}");
        response1.prettyPrint();

        JsonPath json = response1.jsonPath();
        System.out.println("person1`s salary: "+ json.getInt("data.employee_salary"));

        spec.pathParams("firstparameter","api","secondparameter","v1","thirdparameter","employee","fourthparameter",8);
        Response response2= given().spec(spec).when().get("/{firstparameter}/{secondparameter}/{thirdparameter}/{fourthparameter}");
        response2.prettyPrint();

       JsonPath json2= response2.jsonPath();
       System.out.println("person2`s salary: "+ json2.getInt("data.employee_salary"));

        Assert.assertTrue("The expected data does not match actual data!!",json.getInt("data.employee_salary")<json2.getInt("data.employee_salary"));
        Assert.assertTrue("The expected data does not match actual data!!", Integer.parseInt(json.getString("data.employee_salary"))<(Integer.parseInt(json2.getString("data.employee_salary"))) );

    }

}

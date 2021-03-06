package Base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerOkuAppBaseUrl {
    //Create an object in RequestSpecification data type
    // we do not say "we are creating RequestSpecification object"
    // because RequestSpecification is an interface,so we can not make RequestSpecification object
    // Interfaces can be data type, typed on the left but you cannot use it after "new" keyword
    // below RequestSpecification is not constructor, it is data type
    // I want it to be used by child classes, so it is protected.

    protected RequestSpecification spec;

    //If you use @Before annotation at the top of a method, it means the method will be executed before every test method

    @Before
    public void setUp (){

        // With the code written below, spec obj will have your base url, will access your base url

        spec= new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();

    }

}

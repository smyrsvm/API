package Base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyApiBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setUp() {

        spec= new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com").build();




    }




}

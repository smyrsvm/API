package Base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class AgroMonitoringApiBaseUrl {

    protected RequestSpecification spec;

    //If you use @Before annotation at the top of a method, it means the method will be executed before every test method

    @Before
    public void setUp (){

        // with the code written below, spec obj will have your base url, will access your base url

        spec= new RequestSpecBuilder().setBaseUri("http://api.agromonitoring.com").build();

    }






}

package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    // We are separating the test data from test method, take them here
    // With this, we make the test method shorter

    public Map<String,Object> expectedDataSetUp(){

        Map<String,Object> expectedData= new HashMap<>();
        expectedData.put("userId",55);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);
        return expectedData;

    }


















}
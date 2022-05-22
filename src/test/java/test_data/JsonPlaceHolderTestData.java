package test_data;

import io.cucumber.java.it.Ma;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    // We are separating the test data from test method, take them here
    // With this, we make the test method shorter

    public Map<String,Object> expectedDataSetUpWithAllKeys(Integer userId, String title, Boolean completed){

        // We can use this form of method for post, put and get(while creating expected data)

        Map<String,Object> expectedData= new HashMap<>();
        expectedData.put("userId",userId);
        expectedData.put("title",title);
        expectedData.put("completed",completed);

        return expectedData;

    }


    public Map<String,Object> expectedDataSetUpWithMissingKeys(Integer userId, String title, Boolean completed){

        Map<String,Object> expectedData= new HashMap<>();
        if (userId==null){

            expectedData.put("title",title);

        } else if (completed==null){

            expectedData.put("title",title);

        }
        return expectedData;

    }

public String expectedDataInString (Integer userId, String title, Boolean completed){

        String expectedData="{\n" + " \"userId\": " +userId+ ",\n" + " \"title\": "+ title+ ",\n" + " \"completed\": "+completed+ ",\n" + " }";

        return expectedData;

}

}

package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

public class JsonUtil {

    // From ObjectMapper class (Data type ObjectMapper) we create an object.
    // Since we want to reach it with class name, we make it static.

    // Normally we should create our object like below. There is no problem, it works.
    // private static ObjectMapper mapper= new ObjectMapper();
    // But in this case our object is created after our JsonUtil class is loaded.
    // java creates this object after creating our JsonUtil class.
    // So if we want our object ready together with the class, we need to use static block
    // which is used to initialize static objects.
    // If we type our initializing code inside static block, your object is ready together with the class
    // Static block is executed  before everything in the class


    private static ObjectMapper mapper;

    static {
        mapper= new ObjectMapper();
    }

    // 1st Method: It is used to convert JsonDAta to Java Object. De-Serialization Method

    // We make the method static because we want to reach it with JsonUtil class name,
    // we do not want to create object in other classes to reach this method.

    // If we write like below, it works but for example when we want to convert json to a Set,
    // it does not work because it converts json to a HashMap

    //public static HashMap<String,Object> convertJsonToJava(String json) {  }

    // But instead of writing a specific data type, we could write T, meaning every data type is accepted
    // This is called a generic method that works with every kind of data type
    // For example equalTo() works with integer, boolean and String. It is a generic method.

    // This method will give me a java object, so i must put it into a container, variable
    // Why did not we type Object? Object is general class but i need specific class to return that method.
    // When i used this method, I should decide which specific  Java Object will i turn this Json data;
    // to a Hashmap or Set or pojo so i declared it

    public static <T> T convertJsonToJava(String json, Class<T> cls)  { //De-Serialization Method

        T javaResult=null; // It is a local variable.Local variables can not be used without initialization.
        // If it were an instance variable, no need to do assignment.

        try {
            javaResult=mapper.readValue(json,cls); // readValue method gets json and converts it to java object.
        } catch (IOException e) {
            System.out.println("Json could not be converted to Java Object "+ e.getMessage());
        }
        return javaResult;

    }


    /*
     2nd Method: It is used to convert Java Object to JsonDAta. Serialization Method
     everytime we want to get json so no need to make the method generic.
     we could make it String data type. No need to make selection, it is all Json.

     For parameter side; we wrote just Java Object, because we get only JavaObject
     and Object covers all data types.
     Here, we get all data types of Java to json. We do not need to declare a specific data type,
     so I did not make it generic with T...THe important thing is my output to have json, and it is String.
     In the above method, i needed to declare and decide which Java object i will return it.
    */

    public static String convertJavaToJson(Object obj){ // Serialization Method

        String jsonResult =null; // It is a local variable.Local variables can not be used without initialization.
                                 // If it were an instance variable, no need to do assignment.

        try {
            jsonResult=mapper.writeValueAsString(obj);
        } catch (IOException e) {
            System.out.println("Java Object could not be converted to Json "+ e.getMessage());
        }
        return jsonResult;

    }

}

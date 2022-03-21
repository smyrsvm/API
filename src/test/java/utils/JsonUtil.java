package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

public class JsonUtil {

    // From ObjectMapper class (Data type ObjectMapper) we create an object.
    // Since we want to reach it with class name, we make it static.


    // Normally we should create our object like below. There is no problem, it works.
    // But in this case our object is created after our JsonUtil class is loaded.
    // java creates this object after creating our JsonUtil class.
    // So if we want our object ready together with the class we need to use static block
    // which is used to initialize static objects.
    // If we type our initializing code inside static block, your object is ready together with the class
    // Static block is executed everything before  the class
    //private static ObjectMapper mapper= new ObjectMapper();

    private static ObjectMapper mapper;

    static {
        mapper= new ObjectMapper();
    }

    // 1st Method: It is used to convert JsonDAta to Java Object. De-Serialization Method

    // We make the method static because we want to reach it with JsonUtil class name,
    // we do not want to create object in other class to reach the method.

    // If we write like below, it works but for example when we want to convert json to a Set,
    // it does not work because it converts json to a HashMap

    //public static HashMap<String,Object> convertJsonToJava(String json) {  }

    // But instead of writing a specific data type, we could write T, meaning every data type is accepted
    // This is called a generic method that works with every kind of data type
    // For example equalTo() works with integer, boolean and String. It is a generic method.

    //This method will give me a java object, so i must put it to a container, variable
    // Why did not we type Object? Object is general class but i need
    public static <T> T convertJsonToJava(String json, Class<T> cls)  {
        T javaResult=null;

        try {
            javaResult=mapper.readValue(json,cls); // readValue method gets json and converts it to java object.
        } catch (IOException e) {
            System.out.println("Json could not be converted to Java Object"+ e.getMessage());
        }

        return javaResult;
    }


    // 2nd Method: It is used to convert Java Object to JsonDAta. Serialization Method
    // everytime we want to get json so no need to make the method generic.
    // we could make it String data type

    public static String convertJavaToJson(Object obj){


    }















}

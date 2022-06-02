package test_data;

public class HerOkuAppTestData {




    public String expectedDataInString (String firstname,String lastname, Integer totalprice, Boolean depositpaid, Object bookingdates,String checkin, String checkout,String additionalneeds){

        String expectedData="{\n" +
                "\"firstname\":"+firstname+  ",\n" +
                "\"lastname\":"+lastname+ ",\n" +
                " \"totalprice\" :" +totalprice+ ",\n" +
                " \"depositpaid\" :"+depositpaid+",\n" +

                "\"bookingdates\":"+bookingdates+ "{\n" +
                " \"checkin \" :" +checkin + ",\n" +
                " \"checkout \" :" +checkout + ",\n" +
                "},\n" +
                " \"additionalneeds\" :"+additionalneeds+",\n" +
                "}";

        return expectedData;

    }






}

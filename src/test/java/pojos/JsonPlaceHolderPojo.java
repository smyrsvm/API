package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// If our response in json format does not match with the pojo class we created,
// then when we want to change this response json to the pojo classes we created, it alerts and does not accept.
// so we use @JsonIgnoreProperties(ignoreUnknown = true) --> ignore unknown variables
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPlaceHolderPojo {

    //pojo: plain old java object

    // Pojos will help me like setUpMethods
    // With setUpMethods we are creating maps
    // By pojo class constructors i will crate pojo objects and
    // i will use them in expected and actual data



    // Create private variables

    private Integer userID;
    private String title;
    private Boolean completed;

    // Create constructors


    public JsonPlaceHolderPojo(Integer userID, String title, Boolean completed) {
        this.userID = userID;
        this.title = title;
        this.completed = completed;
    }


    public JsonPlaceHolderPojo() {
    }


    // Generate getters and setters


    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {

        this.userID = userID;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public Boolean getCompleted() {

        return completed;
    }

    public void setCompleted(Boolean completed) {

        this.completed = completed;
    }

    // Generate toString()


    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userID=" + userID +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}

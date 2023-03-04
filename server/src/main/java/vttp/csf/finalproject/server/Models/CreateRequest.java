package vttp.csf.finalproject.server.Models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class CreateRequest {
    private String userID;
    private String password;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    public static CreateRequest create(JsonObject jo) {
        CreateRequest w = new CreateRequest();
        w.setUserID(jo.getString("userID"));
        w.setPassword(jo.getString("password"));
        w.setEmail(jo.getString("email"));
        return w;

    }

    // serialisation
    // forming it back to JSON after reading it in the JSON READER
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("userID", userID)
                .add("password", password)
                .add("email", email)
                .build();
    }
}

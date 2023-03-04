package vttp.csf.finalproject.server.Models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class LoginRequest {
    private String userID;
    private String password;

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

   
    public static LoginRequest create(JsonObject jo) {
        LoginRequest w = new LoginRequest();
        w.setUserID(jo.getString("userID"));
        w.setPassword(jo.getString("password"));
        return w;

    }

    // serialisation
    // forming it back to JSON after reading it in the JSON READER
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("userID", userID)
                .add("password", password)
                .build();
    }
}

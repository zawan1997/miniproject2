package vttp.csf.finalproject.server.Models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Location {
    private String name;
    private String body;
    private String primaryContactNo;
    private String closeTime;
    private String primaryFileMediumUuid;

    private String uuid; //primary key for location table

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPrimaryContactNo() {
        return primaryContactNo;
    }

    public void setPrimaryContactNo(String primaryContactNo) {
        this.primaryContactNo = primaryContactNo;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getPrimaryFileMediumUuid() {
        return primaryFileMediumUuid;
    }

    public void setPrimaryFileMediumUuid(String primaryFileMediumUuid) {
        this.primaryFileMediumUuid = primaryFileMediumUuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public static Location create(JsonObject jo) {
        Location loc = new Location();
        loc.setName(jo.getString("name"));
        loc.setBody(jo.getString("body"));
        loc.setPrimaryContactNo(jo.getString("primaryContactNo"));
        loc.setCloseTime(jo.getString("closeTime"));
        loc.setUuid(jo.getString("uuid"));
        return loc;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("name", name)
                .add("body", body)
                .add("primaryContactNo", primaryContactNo)
                .add("closeTime", closeTime)
                // .add("libraryUuid", libraryUuid)
                .add("uuid", uuid)
                .add("primaryFileMediumUuid", primaryFileMediumUuid)

                .build();
    }
}

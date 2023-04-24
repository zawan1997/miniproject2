package vttp.csf.finalproject.server.Models;

import org.springframework.data.annotation.Id;


public class Location {

	private int id;
    private int userId;
    private String locationUuid;
    private String name;
    private String body;
    private String primaryContactNo;
    private String openTime;
    private String closeTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLocationUuid() {
		return locationUuid;
	}
	public void setLocationUuid(String locationUuid) {
		this.locationUuid = locationUuid;
	}
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
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}



}

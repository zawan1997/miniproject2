package vttp.csf.finalproject.server.Models;

import org.springframework.data.annotation.Id;

public class Comment {
    @Id
	private int id;	
	private int userId;
	private int locationId;
	private String text;
	
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
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
    public Object getLocationUuid() {
        return null;
    }
}

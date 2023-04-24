package vttp.csf.finalproject.server.Models;

import org.springframework.data.annotation.Id;

public class ImageComment {
    @Id
	private int id;	
	private int userId;
	private int locationUuid;
	private String blobc;
	
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
	public int getLocationUuid() {
		return locationUuid;
	}
	public void setLocationUuid(int locationId) {
		this.locationUuid = locationId;
	}
	public String getBlobc() {
		return blobc;
	}
	public void setBlobc(String blobc) {
		this.blobc = blobc;
	}

}

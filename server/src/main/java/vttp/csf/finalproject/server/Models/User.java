package vttp.csf.finalproject.server.Models;

import org.springframework.data.annotation.Id;

public class User {
	private int id;
    private String name;
    private String username;
    private String emailId;
    private String password;
    private String profilePic;
    
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getProfilePic() {
        return profilePic;
    }
    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
    
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", username=" + username + ", emailId=" + emailId + ", password="
                + password + ", profilePic=" + profilePic + "]";
    }
}

package vttp.csf.finalproject.server.Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Comment {
    public String username;
    public String text;
    private byte[] image;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }

    public static  Comment populate(ResultSet rs) throws SQLException {
        final Comment post = new Comment();
        post.setUsername(rs.getString("username"));
        post.setText(rs.getString("text"));
        post.setImage(rs.getBytes("blobc"));
        return post;
    }
}

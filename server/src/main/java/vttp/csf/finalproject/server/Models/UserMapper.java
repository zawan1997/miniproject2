package vttp.csf.finalproject.server.Models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
public class UserMapper implements RowMapper<User>{
public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	User user = new User();
	user.setId(rs.getInt("id"));
	user.setName(rs.getString("name"));
	user.setEmailId(rs.getString("email_id"));
	user.setUsername(rs.getString("username"));
	user.setProfilePic(rs.getString("profile_picture"));
	
	try {
		user.setPassword(rs.getString("password"));         
	} catch (SQLException e) {
	}

	return user;
}
}


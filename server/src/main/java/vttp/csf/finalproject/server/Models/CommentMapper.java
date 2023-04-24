package vttp.csf.finalproject.server.Models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

//Same as populate method from ws 
public class CommentMapper implements RowMapper <Comment>{
	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setUserId(rs.getInt("user_id"));
		comment.setLocationUuid(rs.getInt("location_id"));
		comment.setText(rs.getString("text"));

		return comment;
	}
}

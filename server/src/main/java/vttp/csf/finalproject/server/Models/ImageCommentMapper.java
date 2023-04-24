package vttp.csf.finalproject.server.Models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ImageCommentMapper implements RowMapper<ImageComment>{
    @Override
	public ImageComment mapRow(ResultSet rs, int rowNum) throws SQLException {
		ImageComment imageComment = new ImageComment();
		imageComment.setId(rs.getInt("id"));
		imageComment.setUserId(rs.getInt("user_id"));
		imageComment.setLocationId(rs.getInt("location_id"));
		imageComment.setBlobc(rs.getString("blobc"));

		return imageComment;
	}
}

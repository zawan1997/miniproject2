package vttp.csf.finalproject.server.Models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LocationMapper implements RowMapper<Location>{
	
	public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
	Location location = new Location();
	location.setId(rs.getInt("id"));
	location.setUserId(rs.getInt("user_id"));
	location.setLocationUuid(rs.getString("location_uuid"));
	location.setName(rs.getString("name"));
	location.setBody(rs.getString("body"));
	location.setPrimaryContactNo(rs.getString("primaryContactNo"));
	location.setOpenTime(rs.getString("openTime"));
	location.setCloseTime(rs.getString("closeTime"));

	return location;
}

}

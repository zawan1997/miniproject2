package vttp.csf.finalproject.server.Repositories;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp.csf.finalproject.server.Models.Location;
import vttp.csf.finalproject.server.Models.LocationMapper;

@Repository
public class LocationRepo  {
    JdbcTemplate jdbcTemplate;

    private final String SQL_SAVE_USER_LOCATION = "insert into user_locations (user_id, location_uuid, name, body, primaryContactNo,"
            + " openTime,"
            + " closeTime) "
            + "values (?,?,?,?,?,?,?)";
    private final String SQL_DELETE_USER_LOCATION = "delete from user_locations where id = ?";
    private final String SQL_UPDATE_USER_LOCATION = "update user_locations set "
            + "name = ?, body = ?, primaryContactNo  = ?, openTime  = ?, closeTime  = ? where id = ?";
    private final String SQL_GET_ALL = "select * from user_locations";
    private final String SQL_FIND_USER_LOCATION = "select * from user_locations where id = ?";

    @Autowired
    public LocationRepo(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @SuppressWarnings("deprecation")
    public Location getLocationById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_USER_LOCATION, new Object[] { id }, 
                new LocationMapper());
    }

    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_GET_ALL, new LocationMapper());
    }

    public boolean deleteLocation(int id) {
        return jdbcTemplate.update(SQL_DELETE_USER_LOCATION, id) > 0;
    }

    public boolean updateLocation(Location location) {
        return jdbcTemplate.update(SQL_UPDATE_USER_LOCATION, 
                location.getName(), 
                location.getBody(),
                location.getPrimaryContactNo(),
                location.getOpenTime(),
                location.getCloseTime(), 
                location.getId()) > 0;
    }

    public boolean createLocation(Location location) {
        return jdbcTemplate.update(SQL_SAVE_USER_LOCATION, 
                location.getUserId(), 
                location.getLocationUuid(),
                location.getName(), 
                location.getBody(),
                location.getPrimaryContactNo(),
                location.getOpenTime(),
                location.getCloseTime()) > 0;
    }

}

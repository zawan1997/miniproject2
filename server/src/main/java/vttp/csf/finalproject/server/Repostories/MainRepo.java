package vttp.csf.finalproject.server.Repostories;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import javax.sql.DataSource;
import javax.tools.DocumentationTool.Location;
import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartFile;

import vttp.csf.finalproject.server.Models.CreateRequest;

public class MainRepo {
    
    @Autowired
    private JdbcTemplate template;
    
    @Autowired
    private DataSource datasource;
   
    public void uploadBlob(MultipartFile file, String username, String comment, String uuid) {
        try (Connection con = datasource.getConnection();
        PreparedStatement pstmnt = con.prepareStatement(INSERT_COMMENT_TBL)) {
        InputStream is = file.getInputStream();
            pstmnt.setBinaryStream(1, is, file.getSize());
            pstmnt.setString(2, comment);
            pstmnt.setString(3, username);
            pstmnt.setString(4, uuid);
        }
    } 

    //ID primary key for both user accounts and saving
    public Optional<Location> getLocationByUserid(Integer userId) {
        return template.query(
            SQL_GET_POST_BY_USERID,
            (ResultSet rs) -> {
                if(!rs.next())
                return Optional.empty();
                final Location location = location.populate(rs);
                return Optional.of(location);
            },
            userId
        );
    }
    //Must map location to key userid 
    public void saveLocationsPerUser(Location location, CreateRequest cr) {
        return template.update(SQL_SAVE_LOCATION_TO_USER,
        location, cr.getUserID()) > 0;
    }
    //random userId per user
    //How to create else statement 
    public boolean saveUser(CreateRequest create) {
        return template.update(SQL_SAVE_USER, create) > 0;
    }
    
    public boolean isUserValid(CreateRequest create) {

    }

}

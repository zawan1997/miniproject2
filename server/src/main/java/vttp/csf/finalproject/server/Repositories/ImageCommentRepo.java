package vttp.csf.finalproject.server.Repositories;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp.csf.finalproject.server.Models.ImageComment;
import vttp.csf.finalproject.server.Models.ImageCommentMapper;

@Repository
public class ImageCommentRepo  {
	JdbcTemplate jdbcTemplate;

    private final String SQL_SAVE_Image_COMMENT = "insert into image_comments (user_id, location_uuid, blobc) "
            + "values (?,?,?)";
    private final String SQL_DELETE_Image_COMMENT = "delete from image_comments where id = ?";
    private final String SQL_UPDATE_Image_COMMENT = "update image_comments set "
            + "text = ? where id = ?";
    private final String SQL_GET_ALL = "select * from image_comments";
    private final String SQL_FIND_Image_COMMENT = "select * from image_comments where id = ?";
    private final String SQL_GET_LOCATION_IMAGE_COMMENTS = "select * from image_comments where location_uuid = ";

    @Autowired
    public ImageCommentRepo(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @SuppressWarnings("deprecation")
    public ImageComment getImageCommentById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_Image_COMMENT, new Object[] { id }, 
                new ImageCommentMapper());
    }

    public List<ImageComment> getAllImageComments() {
        return jdbcTemplate.query(SQL_GET_ALL, new ImageCommentMapper());
    }

    public boolean deleteImageComment(int id) {
        return jdbcTemplate.update(SQL_DELETE_Image_COMMENT, id) > 0;
    }

    public boolean updateImageComment(ImageComment imageComment) {
        return jdbcTemplate.update(SQL_UPDATE_Image_COMMENT, 
                imageComment.getBlobc(),
                imageComment.getId()) > 0;
    }

    public boolean createImageComment(ImageComment imageComment) {
        return jdbcTemplate.update(SQL_SAVE_Image_COMMENT, 
                imageComment.getUserId(),
                imageComment.getLocationUuid(), 
                imageComment.getBlobc()) > 0;
    }

    public List<ImageComment> getLocationImageComments(String locationUuid) {
        return jdbcTemplate.query(SQL_GET_LOCATION_IMAGE_COMMENTS+"'"+locationUuid+"'", new ImageCommentMapper());
    }

}

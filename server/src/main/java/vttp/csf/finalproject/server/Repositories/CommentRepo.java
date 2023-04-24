package vttp.csf.finalproject.server.Repositories;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp.csf.finalproject.server.Models.Comment;
import vttp.csf.finalproject.server.Models.CommentMapper;

@Repository

public class CommentRepo {
    JdbcTemplate jdbcTemplate;

    private final String SQL_SAVE_COMMENT = "insert into comments (user_id, location_uuid, text) "
            + "values (?,?,?)";
    private final String SQL_DELETE_COMMENT = "delete from comments where id = ?";
    private final String SQL_UPDATE_COMMENT = "update comments set "
            + "text = ? where id = ?";
    private final String SQL_GET_ALL = "select * from comments";
    private final String SQL_FIND_COMMENT = "select * from comments where id = ?";
    private final String SQL_GET_LOCATION_COMMENTS = "select * from comments where location_uuid = ";

    @Autowired
    public CommentRepo(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @SuppressWarnings("deprecation")
    public Comment getCommentById(int id) {
        return jdbcTemplate.queryForObject(SQL_FIND_COMMENT, new Object[] { id }, 
                new CommentMapper());
    }

    public List<Comment> getAllComments() {
        return jdbcTemplate.query(SQL_GET_ALL, new CommentMapper());
    }

    public boolean deleteComment(int id) {
        return jdbcTemplate.update(SQL_DELETE_COMMENT, id) > 0;
    }

    public boolean updateComment(Comment comment) {
        return jdbcTemplate.update(SQL_UPDATE_COMMENT, 
                comment.getText(),
                comment.getId()) > 0;
    }

    public boolean createComment(Comment comment) {
        return jdbcTemplate.update(SQL_SAVE_COMMENT, 
                comment.getUserId(),
                comment.getLocationUuid(), 
                comment.getText()) > 0;
    }
    
    public List<Comment> getLocationComments(String locationUuid) {
        return jdbcTemplate.query(SQL_GET_LOCATION_COMMENTS+"'"+locationUuid+"'", new CommentMapper());
    }

}

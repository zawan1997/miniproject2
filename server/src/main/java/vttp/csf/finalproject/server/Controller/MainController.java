package vttp.csf.finalproject.server.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import vttp.csf.finalproject.server.Services.CommentService;
import vttp.csf.finalproject.server.Services.ImageCommentService;
import vttp.csf.finalproject.server.Services.LocationService;
import vttp.csf.finalproject.server.Services.UserService;

public class MainController {
    
    @Autowired
    CommentService commentService;

    @Autowired
    ImageCommentService imageService;

    @Autowired
    UserService userService;

    @Autowired
    LocationService locationService;

    

}

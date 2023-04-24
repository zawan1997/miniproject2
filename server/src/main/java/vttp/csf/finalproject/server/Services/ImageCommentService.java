package vttp.csf.finalproject.server.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.csf.finalproject.server.Models.ImageComment;
import vttp.csf.finalproject.server.Repositories.ImageCommentRepo;

@Service
public class ImageCommentService implements MainServiceInt<ImageComment>{
	@Autowired
    ImageCommentRepo imageCommentRepo;

    @Override
    public ImageComment create(ImageComment imageCommentReq) {      
        boolean res = imageCommentRepo.createImageComment(imageCommentReq);
        System.out.println("res : "+res);
        
        return imageCommentReq;
    }
    
    @Override
    public ImageComment update(ImageComment imageCommentReq) {      
        boolean res = imageCommentRepo.updateImageComment(imageCommentReq);
        System.out.println("res : "+res);
        
        return imageCommentReq;
    }
    
    @Override
    public List<ImageComment> get() {       
        List<ImageComment> res = imageCommentRepo.getAllImageComments();
        System.out.println("res : "+res);
        
        return res;
    }

    @Override
    public ImageComment get(int id) {       
        ImageComment res = imageCommentRepo.getImageCommentById(id);
        System.out.println("res : "+res);
        
        return res;
    }

    
    public List<ImageComment> getLocationImageComments(String locationUuid) {       
        List<ImageComment> res = imageCommentRepo.getLocationImageComments(locationUuid);
        System.out.println("res : "+res);
        
        return res;
    }
    
    @Override
    public String delete(int id) {      
        boolean res = imageCommentRepo.deleteImageComment(id);
        System.out.println("res : "+res);
        
        
        return "Comment Delete Response is "+res;
    }

}


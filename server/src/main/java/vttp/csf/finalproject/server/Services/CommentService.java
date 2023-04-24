package vttp.csf.finalproject.server.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.csf.finalproject.server.Models.Comment;
import vttp.csf.finalproject.server.Repositories.CommentRepo;

@Service
public class CommentService implements MainServiceInt<Comment>{
	@Autowired
	CommentRepo commentRepo;

	@Override
	public Comment create(Comment commentReq) {		
		boolean res = commentRepo.createComment(commentReq);
		System.out.println("res : "+res);
		
		return commentReq;
	}
	
	@Override
	public Comment update(Comment commentReq) {		
		boolean res = commentRepo.updateComment(commentReq);
		System.out.println("res : "+res);
		
		return commentReq;
	}
	
	@Override
	public List<Comment> get() {		
		List<Comment> res = commentRepo.getAllComments();
		System.out.println("res : "+res);
		
		return res;
	}

	@Override
	public Comment get(int id) {		
		Comment res = commentRepo.getCommentById(id);
		System.out.println("res : "+res);
		
		return res;
	}
	
	public List<Comment> getLocationComments(String locationUuid) {		
		List<Comment> res = commentRepo.getLocationComments(locationUuid);
		System.out.println("res : "+res);
		
		return res;
	}

	@Override
	public String delete(int id) {		
		boolean res = commentRepo.deleteComment(id);
		System.out.println("res : "+res);
		
		
		return "Comment Delete Response is "+res;
	}
}
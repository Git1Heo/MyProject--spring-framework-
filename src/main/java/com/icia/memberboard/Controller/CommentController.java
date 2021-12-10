package com.icia.memberboard.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.memberboard.Service.CommentService;
import com.icia.memberboard.dto.CommentDTO;

@Controller
public class CommentController {
	@Autowired
	private CommentService cs;
	
	@RequestMapping (value ="/board/comment/save", method=RequestMethod.POST)
	public  @ResponseBody List<CommentDTO> save (@ModelAttribute CommentDTO comment,Model model){
		System.out.println("commentController.save() : " + comment);
		cs.save(comment);
		
		List<CommentDTO> commentList = cs.findAll(comment.getB_number());
		return commentList;
		}
}

package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.CommentDao;
import com.test.domain.Comment;

@Service
public class CommentService {
	
	@Autowired
	private CommentDao commentDao;
	
	
	public void addComment(Comment comment) {
		Integer maxNum = commentDao.getMaxNum();
		if (maxNum.equals(null)) {
			comment.setComment_num(1);
		} else {
			comment.setComment_num(maxNum + 1);
		}
		System.out.println("Comment_num: "+comment.getComment_num());
		
		comment.setRe_ref(comment.getComment_num());
		comment.setRe_lev(0);
		comment.setRe_seq(0);
		comment.setReadcount(0);
		System.out.println(comment.toString());
		commentDao.addComment(comment);
		
	}
	
	public List<Comment> getCommentList(int restaurant_num){
		List<Comment> comments = commentDao.getCommentList(restaurant_num);
		
		return comments;
	}
}

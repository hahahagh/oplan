package com.test.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.domain.Comment;
import com.test.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	@RequestMapping(value="/addComment" , method=RequestMethod.GET)
	public String add() {
		
		return "comment/comment_form";
	}
	@RequestMapping(value="/addComment" , method=RequestMethod.POST)
	public String add(HttpServletRequest request, HttpSession session, 
			@ModelAttribute Comment comment, ModelMap modelMap) {
		session = request.getSession();
		String id = (String) session.getAttribute("id");
		// 세션값 없으면 /member/loginForm 으로 이동
		if (id == null) {
			return "redirect:/app/member/login";
		}
		// num pageNum 가져오기
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		System.out.println("pageNum: "+pageNum);
		// 댓글쓴이 가져오기
		String member_id = request.getParameter("member_id");
		System.out.println("member_id: "+member_id);
		int restaurant_num = Integer.parseInt(request.getParameter("restaurant_num"));
		System.out.println("restaurant_num: "+restaurant_num);
		
		comment.setRestaurant_num(restaurant_num);
		comment.setMember_id(member_id);
		comment.setReg_date(new Timestamp(System.currentTimeMillis()));
		comment.setIp(request.getRemoteAddr());
		
		System.out.println(comment.toString());
		
		commentService.addComment(comment);
		modelMap.addAttribute(member_id);
		modelMap.addAttribute(pageNum);
		
		return "redirect:/app/member/main";
	}
	@RequestMapping("/list")
	public String list(HttpSession session, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
		int restaurant_num = Integer.parseInt(request.getParameter("restaurant_num"));
		List<Comment> comments = commentService.getCommentList(restaurant_num);
		modelMap.addAttribute("comments", comments);
		return null;
	}
}

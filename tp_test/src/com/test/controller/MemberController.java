package com.test.controller;

import java.io.PrintWriter;
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
import org.springframework.web.servlet.ModelAndView;

import com.test.domain.Member;
import com.test.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	// Springanno/app/member/addForm 요청시 호출되는 메서드
	// view-source:localhost:8181/springanno/app/member/add
	// GET 방식 요청
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add() {
		System.out.println("addForm");
		return "member/addForm"; // 포워딩방식으로 jsp파일을 사용하라는 정보 리턴
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@ModelAttribute Member member) {
		System.out.println("addSubmit");
		// Member 오브젝트 생성 후  파라미터가 채워져서 매개변수로 전달받음
		member.setReg_date(new Timestamp(System.currentTimeMillis()));
		memberService.add(member);
		
		return "redirect:/app/member/login"; //프론트 컨트롤러가 받아들이는 명령어
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm() {
		return "member/loginForm";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginSubmit(@RequestParam("id") String id, 
			@RequestParam String passwd, HttpServletResponse response, HttpSession session)throws Exception {
		
		int check = memberService.userCheck(id, passwd);
		
		if (check != Member.ID_OK_PASSWD_OK) { // 아이디, 패스워드가 일치하지 않으면
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			String message = "";
			switch (check) {
			case Member.ID_OK_PASSWD_FAIL: message = "패스워드 틀림"; break;
			case Member.ID_FAIL_PASSWD_FAIL : message = "아이디 없음"; break;
			}
			
			out.println("<script>alert('" + message + "');"
					+ "history.back();</script>");
			out.close();
			return null;
		}
		
		session.setAttribute("id", id);
		
		return "redirect:/app/member/main";
		
	}
	
	@RequestMapping("/main")
	public String main() {
		return "member/main";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/app/member/login";
	}
	
	@RequestMapping("/get")
	public String get(HttpSession session, ModelMap modelMap) {
		String id = (String) session.getAttribute("id");
		
		if(id == null) {
			return "redirect:/app/member/login";
		}
		Member member = memberService.get(id);
		modelMap.addAttribute("member", member);
		
		return "member/info";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String updateForm(HttpSession session, ModelMap modelMap) {
		
		String id = (String) session.getAttribute("id");
		
		if(id == null) {
			return "redirect:/app/member/login";
		}
		Member member = memberService.get(id);
		modelMap.addAttribute("member", member);
		
		return "member/updateForm";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(HttpSession session, @ModelAttribute Member member, HttpServletResponse response) throws Exception{
		
		String id = (String) session.getAttribute("id");
		
		if(id == null) {
			return "redirect:/app/member/login";
		}
		
		int check = memberService.userCheck(member.getId(), member.getPasswd());
		if (check == Member.ID_OK_PASSWD_FAIL) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('패스워드 틀림');history.back();</script>");
			out.close();
			return null;
		}
		
		memberService.update(member);
		return "redirect:/app/member/main";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deleteForm(HttpSession session, ModelMap modelMap) {
		String id = (String) session.getAttribute("id");
		
		if(id == null) {
			return "redirect:/app/member/login";
		}
		Member member = memberService.get(id);
		modelMap.addAttribute("member", member);
		
		return "member/deleteForm";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@RequestParam String id, @RequestParam String passwd 
			,HttpSession session, HttpServletResponse response) throws Exception{
		// 세션값 없으면 /member/loginForm 으로 이동
		if (id == null) {
			return "redirect:/app/member/login";
		}
		// passwd 파라미터 가져오기

		int check = memberService.userCheck(id, passwd);
		if (check == Member.ID_OK_PASSWD_FAIL) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('패스워드 틀림');history.back();</script>");
			out.close();
			return null;
		}
		
		memberService.delete(id);
		session.invalidate(); // 세션값 초기화
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('회원 삭제되었습니다.');"
				+ "location.href='login';</script>");
		out.close();
		
		return "redirect:/app/member/login";
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		
		String id = (String) session.getAttribute("id");
		// 세션값 없거나 admin이 아니면 /member/main 으로 이동
		if (id == null || !id.equals("admin")) {
			return "redirect:/app/member/main";
		}
		
		List<Member> members = memberService.getAll();
		modelMap.addAttribute("members", members);
		return "member/list";
	}
	
}

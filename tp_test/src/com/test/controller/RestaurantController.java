package com.test.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.test.domain.Board;
import com.test.domain.Comment;
import com.test.domain.Restaurant;
import com.test.service.CommentService;
import com.test.service.RestaurantService;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "restaurant/addForm";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpSession session, @RequestParam("file") MultipartFile multipartFile,
			@ModelAttribute Restaurant restaurant, HttpServletRequest request) throws Exception {

		String id = (String) session.getAttribute("id");
		if (id == null) {
			return "redirect:/app/member/login";
		}

		// 파일 업로드 수행
		String filename = ""; // 파일명 초기화
		if (!multipartFile.isEmpty()) { // 파일 있으면
			filename = multipartFile.getOriginalFilename(); // 파일명 가져오기
			String realPath = session.getServletContext().getRealPath("/upload"); // 업로드 경로 가져오기

			File file = new File(realPath, filename);
			if (file.exists()) { // 해당 경로안에 동일한 파일명이 이미 존재할 경우
				// 파일명 앞에 업로드 시간 밀리초 단위로 붙여 파일명 중복을 방지
				filename = System.currentTimeMillis() + "_" + filename;
				file = new File(realPath, filename);
			}

			System.out.println("업로드 경로: " + realPath);
			System.out.println("업로드 파일명: " + filename);

			// 업로드 수행
			IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
		} else {
			System.out.println("파일이 존재하지 않거나 파일크기가 0입니다.");
		}

		// DB insert
		restaurant.setFilename(filename); // 파일명 저장
		restaurant.setReg_date(new Timestamp(System.currentTimeMillis()));

		restaurantService.add(restaurant);

		return "redirect:/app/member/main";
	} // add

	@RequestMapping("/list")
	public String getList(HttpSession session, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {

		String id = (String) session.getAttribute("id");
		// 세션값 없으면 /member/loginForm 으로 이동
		if (id == null) {
			return "redirect:/app/member/login";
		}
		// 매장갯수 가져오기
		int totalRowCount = restaurantService.getRestaurantCount();

		// 우리가 원하는 페이지 글 가져오기

		// 한페이지 당 보여줄 글 개수!!
		int pageSize = 5;
		// 클라이언트가 전송하는 페이지번호를 기준으로
		// 가져올 글의 시작행번호와 종료행번호를 계산하면 됨.
		/*
		 * String strPageNum = request.getParameter("pageNum"); if (strPageNum == null
		 * || strPageNum.equals("")) { strPageNum = "1"; } int pageNum =
		 * Integer.parseInt(strPageNum); // 페이지번호
		 */
		// 시작행번호 구하기 공식
		int startRow = (pageNum - 1) * pageSize + 1;
		// 종료행번호 구하기 공식
		int endRow = pageNum * pageSize;
		// 원하는 페이지의 글을 가져오는 메소드
		List<Restaurant> restaurants = restaurantService.getRestaurantList(startRow, endRow);

		// 전체 페이지블록 갯수 구하기
		// 글갯수50개, 한화면보여줄글10개 => 50/10 = 몫5 + 나머지0 = 페이지블록5개
		// 글갯수52개, 한화면보여줄글10개 => 52/10 = 몫5 + 나머지2 = (+1)페이지블록6개
		int pageCount = totalRowCount / pageSize + (totalRowCount % pageSize == 0 ? 0 : 1);

		// 한 화면에 보여줄 페이지블록 갯수 설정
		int pageBlock = 3;

		// 화면에 보여줄 "페이지블록 범위내의 시작번호" 구하기
		// 1~10 11~20 21~30
		// 1~10 => 1 11~20 => 11
		int startPage = (pageNum / pageBlock - (pageNum % pageBlock == 0 ? 1 : 0)) * pageBlock + 1;

		// 화면에 보여줄 "페이지블록 범위내의 끝번호" 구하기
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		modelMap.addAttribute("totalRowCount", totalRowCount);
		modelMap.addAttribute("pageNum", pageNum);
		modelMap.addAttribute("restaurants", restaurants);
		modelMap.addAttribute("pageCount", pageCount);
		modelMap.addAttribute("pageBlock", pageBlock);
		modelMap.addAttribute("startPage", startPage);
		modelMap.addAttribute("endPage", endPage);

		return "restaurant/list";
	} // getList
	
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request, HttpSession session, ModelMap modelMap) {
		session = request.getSession();
		String id = (String) session.getAttribute("id");
		// 세션값 없으면 /member/loginForm 으로 이동
		if (id == null) {
			return "redirect:/app/member/login";
		}
		// num pageNum 가져오기
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		Restaurant restaurant = restaurantService.getRestaurant(num);

		// 저장
		modelMap.addAttribute("restaurant", restaurant);
		modelMap.addAttribute("pageNum", pageNum);
		modelMap.addAttribute("id",id);
		return "restaurant/content";
	}
	
	
	

}

package com.ezen.myProject.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.myProject.domain.UserVO;
import com.ezen.myProject.service.UserService;


@RequestMapping("/member/*") // 멤버라는 이름의 모든값을 받겠다.
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject //내가 만든 서비스를 userService에서 사용할수 있게끔 밀어넣기
	private UserService userService;
	
	@GetMapping("/") 	
	public ModelAndView home(ModelAndView mv) {
		mv.setViewName("/home"); // dustPage라고 생각하면 됨
		return mv;
	}
	
	@GetMapping("/signup")
	public ModelAndView signUpGet(ModelAndView mv) {
		mv.setViewName("/user/signup");
		return mv;
	}
	
	@PostMapping("/signup")
	public ModelAndView signUpPost(ModelAndView mv, UserVO user) {
		logger.info(user.toString());
		//비밀번호 암호화 설정 완료 후
		boolean isUp = userService.signUp(user); // 서비스 구현
		if(isUp) {
			mv.setViewName("/user/login");
		}else {
			mv.setViewName("/user/signup"); // 로그인이 실패했을경우 회원가입으로 보냄
			mv.addObject("msg","0"); // 로그인이 실패했을경우
		}
		return mv;
	}
	
	@GetMapping("/login")
	public ModelAndView loginGet(ModelAndView mv) {
		mv.setViewName("/user/login");
		return mv;
	}
	
	@PostMapping("/login")
	public ModelAndView loginPost(ModelAndView mv, String id, String pw, HttpServletRequest req) {
		//id,pw를 받아서 일치하는 회원이 있으면 회원정보를 가져오고 없으면 null
		logger.info(">>>> id : " + id + ">>>> pw : " + pw);
		UserVO isUser = userService.isUser(id,pw);
		
		if(isUser != null) {
		HttpSession ses = req.getSession();
		ses.setAttribute("ses", isUser);
		
		mv.addObject("user", isUser);
		mv.addObject("msg","1");
		mv.setViewName("/home");
		}else {
			mv.setViewName("/user/login"); //로그인 실패시
			mv.addObject("msg", "0");
		}
		return mv;
	}
	@GetMapping("/logout")
	public ModelAndView logoutGet(ModelAndView mv, HttpServletRequest req) {
		mv.addObject("msg","0");
		req.getSession().removeAttribute("ses");
		req.getSession().invalidate();	
		mv.setViewName("redirect:/");
		
		
		return mv;
	}
	
	@GetMapping("/modify")
	public ModelAndView modifyGet(ModelAndView mv) {
		mv.setViewName("/user/modify");
		return mv;
	}
	
	@PostMapping("/update")
	public ModelAndView modifyPost(ModelAndView mv, UserVO user, HttpServletRequest req) {
		logger.info(">>> user" + user.toString());
		boolean updateUser = userService.updateUser(user);
		if(updateUser) {
			mv.setViewName("/home");
		}else {
			mv.setViewName("/user/login");
		}
		
		return mv;
	}
	
}

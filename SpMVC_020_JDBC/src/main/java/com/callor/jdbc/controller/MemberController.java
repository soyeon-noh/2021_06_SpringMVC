package com.callor.jdbc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.jdbc.model.UserVO;
import com.callor.jdbc.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/member")
public class MemberController {

	protected final MemberService mService;
	public MemberController(MemberService mService) {
		this.mService = mService;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "member/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(
			@RequestParam("m_username") String username,
			@RequestParam("m_password") String password,
			HttpSession hSession) {
		// form에서 전송되는건 m_password라는 이름인데 
		// 난여기서 그냥 password라는 이름으로 쓰고 싶을 때 사용하는 방법
		
		log.debug("사용자ID {} ", username);
		log.debug("비밀번호 {} ", password);
		
		UserVO userVO = mService.login(username, password);
		hSession.setAttribute("USERVO", userVO);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "member/join";
	}
}

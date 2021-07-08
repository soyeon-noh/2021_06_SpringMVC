package com.callor.gallery.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.callor.gallery.model.MemberVO;
import com.callor.gallery.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping(value="/member")
public class MemberController {

	protected final MemberService mService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(Model model) {
		model.addAttribute("BODY","JOIN");
		return "home";
	}

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(MemberVO memberVO, Model model) {
		
		log.debug("회원가입 정보 {}", memberVO.toString()); // 잘넘어오나 확인!
		
		memberVO = mService.join(memberVO);
		
		model.addAttribute("BODY","JOIN");
		return "home";
	}	
	
	@ResponseBody
	@RequestMapping(value="/id_check", method=RequestMethod.GET)
	public String id_check(String m_userid) {
		
		log.debug("중복 검사를 수행할 ID: {}", m_userid);
		MemberVO memberVO = mService.findById(m_userid);
		
		if(memberVO == null) {
			return "NOT_USE_ID";
		}else {
			return "USE_ID";
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model) {
		
		model.addAttribute("BODY", "LOGIN");
		return "home";
		
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(MemberVO memberVO, 
					Model model, // 한번 값을 보내면 소멸
					HttpSession session) { // 컴퓨터를 종료하거나, 지우거나, 시간이 만료되기 전까지 존재
		
		memberVO = mService.login(memberVO, model);
		if(memberVO == null) { // 로그인 실패!
			
			model.addAttribute("BODY", "LOGIN"); // add 는 return된 다음 소멸됨
			return "home";			
		} else { // 로그인 성공!
			
			// 사용자 ID 정상, 비밀번호 확인 정상
			// HttpSession에 사용자 정보가 담긴 memberVO를
			// 속성으로 setting한다. 
			// ( set은 사용에 주의해야함. 너무 많으면 서버에 부담이 간다...
			//			로그인 100만명하면 MEMBER 객체가 100만개 생기고 계속 남아있는다.)
			session.setAttribute("MEMBER", memberVO); // set 은 남아있는다
			
			/*
			 * HttpSession에 속성으로 setting 된 값은
			 * 		어떠한 type이라도 상관없다.
			 * 하지만 HttpSession에 담긴 속성은
			 * 		임의로 삭제하거나 초기화하거나
			 * 		서버가 멈추거나, 일정 조건이 성립되지 않으면
			 * 		서버 메모리에 영구히 남아있다.
			 * 
			 * 1. 가급적 작은 크기의 데이터만 담아야한다.
			 * 2. 필요없으면 반드시 소멸시켜라
			 * 3. 자동 소멸되는 조건을 잘 지정하라
			 * 4. 
			 */
			
			// session.setAttribute("USERID", memberVO.getM_userid());
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.removeAttribute("MEMBER"); 
		
		return "redirect:/";
	}
}

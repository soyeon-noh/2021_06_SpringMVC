package com.callor.score.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.score.model.StudentVO;
import com.callor.score.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping(value="/student")
@Slf4j
@Controller
public class StudentController {

	protected final StudentService stService;
	public StudentController(StudentService stService) {
		this.stService = stService;
	}
	
	@RequestMapping(value={"/",""}, method = RequestMethod.GET)
	public String List(Locale locale, Model model) {
		
		List<StudentVO> stList = stService.selectAll();
		log.debug("Controller stService.selectAll {}", stList);
		model.addAttribute("STUDENTS", stList);
		
		// String BODY = "SUDENT_LIST;
		// view.rendering(BODY);
		// 위 코드가 이런 것과 같다. 
		// 키값에는 문자열, 뒤에는 무슨 형태든 넣을 수 있어 편리.
		
		model.addAttribute("BODY", "STUDENT_LIST");
		
		return "home";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(Model model) {
		
		model.addAttribute("BODY", "STUDENT_INPUT");
		return "home";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(StudentVO studentVO, Model model) {
		
//		stService.insert
		
		
		model.addAttribute("BODY", "STUDENT_INPUT");
		return "home";
	}
}

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

@Slf4j
@Controller
@RequestMapping(value = "/student")
public class StudentController {

	protected final StudentService stService;
	
	public StudentController(StudentService stService) {
		this.stService = stService;
	}
	
	@RequestMapping(value= { "/", "" }, method = RequestMethod.GET)
	public String student(Locale locale, Model model) {
		
		List<StudentVO> stList = stService.showList();
		log.debug("studentList 가져오기 : {}", stList);
		model.addAttribute("STLIST",stList);
		
		return "student/list";
	}
	
}

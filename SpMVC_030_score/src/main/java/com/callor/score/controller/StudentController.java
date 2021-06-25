package com.callor.score.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.score.model.StudentVO;
import com.callor.score.model.SubjectAndScoreDTO;
import com.callor.score.service.ScoreService;
import com.callor.score.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping(value="/student")
@RequiredArgsConstructor
@Slf4j
@Controller
public class StudentController {

	protected final StudentService stService;
	protected final ScoreService scService;

	
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
	
	// 현재연도
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(Model model) {
		
		StudentVO stVO = new StudentVO();
	    stVO.setSt_num(stService.makeStNum());
		
	    model.addAttribute("STD", stVO);
		model.addAttribute("BODY", "STUDENT_INPUT");
		return "home";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(StudentVO studentVO, Model model) {
		
		log.debug("Req 학생정보 : {}", studentVO.toString());
		
		int ret = stService.insert(studentVO);
		
		model.addAttribute("BODY", "STUDENT_INPUT");
		return "redirect:/student";
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String detail(String st_num, Model model) {
		
		List<SubjectAndScoreDTO> ssList 
			= scService.selectScore(st_num);
		
		// StudentVO stVO = stService.find
		
		model.addAttribute("SSLIST", ssList);
		model.addAttribute("BODY", "STUDENT_DETAIL");
		return "home";
		
	}
}

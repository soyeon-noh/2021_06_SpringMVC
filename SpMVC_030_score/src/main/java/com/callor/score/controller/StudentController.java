package com.callor.score.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.score.model.ScoreInputVO;
import com.callor.score.model.StudentVO;
import com.callor.score.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping(value="/student")
@RequiredArgsConstructor
@Slf4j
@Controller
public class StudentController {

	protected final StudentService stService;
	//protected final ScoreService scService;

	
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
		
//		List<SubjectAndScoreDTO> ssList 
//			= scService.selectScore(st_num);
		
		String ret = stService.detail(model, st_num);
		
		// StudentVO stVO = stService.find
//		model.addAttribute("SSLIST", ssList);
		model.addAttribute("BODY", "STUDENT_DETAIL");
		return "home";
		
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.POST)
//	public String detail( //여기서는 RequestParam이 필수다!!
//			@RequestParam(name="subject") List<String> subject,
//			@RequestParam(name="score") List<String> score) {
	
	public String detail(ScoreInputVO scInputVO, Model model) {
		
//		log.debug("Subject: {} ", subject.toString());
//		log.debug("Score: {} ", score.toString());
		
		log.debug("Score Input {}", scInputVO.toString());
		// 여기 form에는 점수랑 과목밖에 없는데 num가 어떻게 들어왔냐...
		// 주소창에 ?stnum = 어쩌고 한게 그게 그냥 가져와진대... 
		// 너무 어이없다..
		
		String ret = stService.scoreInput(scInputVO);
		String st_num = scInputVO.getSt_num();
		model.addAttribute("st_num", st_num); 
		// 모델에 임의의 변수하나를 생성하면 st_num=20210102처럼 변수가따라간다
		
		/* 
		 * redirect를 수행할 때 query string 을 보내고 싶으면
		 * 해당 변수와 값을 model에 속성(Attribute)로 추가(add)
		 * 
		 * redirect:/studetn/detail?st_num=" + st_num과 같이 사용하지 않아도 된다.
		 * 
		 */
		
		return "redirect:/student/detail";
		// return "redirect:/student/detail?st_num" + st_num 으로 할 필요없다!
	}
}

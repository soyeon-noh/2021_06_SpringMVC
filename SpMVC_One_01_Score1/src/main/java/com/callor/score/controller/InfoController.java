package com.callor.score.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.score.model.HomeDTO;
import com.callor.score.model.ScoreVO;
import com.callor.score.model.StudentVO;
import com.callor.score.service.HomeService;
import com.callor.score.service.ScoreService;
import com.callor.score.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping(value = "/info")
@Slf4j
@Controller
public class InfoController {

	protected final HomeService homeService;
	protected final ScoreService scService;
	protected final StudentService stService;
	
	public InfoController(HomeService homeService,
						ScoreService scService,
						StudentService stService) {
		this.homeService = homeService;
		this.scService = scService;
		this.stService = stService;
	}
	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String info(String num, Locale locale, Model model) {
		
		HomeDTO info = homeService.findById(num);
		log.debug("학번의 info 가져오기: {}", info);
		model.addAttribute("IF", info);
		
		List<ScoreVO> scList = scService.showfindByNum(num);
		log.debug("학번의 scoreList 가져오기: {}", scList);
		model.addAttribute("SCORES", scList);
		
		return "info/home";
	}
	
	@RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
	public String insert(ScoreVO scoreVO, Locale locale, Model model) {
		
		int score = scService.insertScore(scoreVO);
		log.debug("점수 insert: {}", scoreVO);
		
		String num = scoreVO.getSc_stnum();
		
		return "redirect:/info?num=" + num;
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String student(StudentVO stVO, Locale locale, Model model) {
		
		//여기작성해야함
		
		
		return "/info/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(StudentVO stVO, Locale locale, Model model) {
		
		int student = stService.studentUpdate(stVO);
		log.debug("학생 update: {}", student);
		
		String num = stVO.getSt_num();
		
		return "redirect:/info?num=" + num;
	}
}

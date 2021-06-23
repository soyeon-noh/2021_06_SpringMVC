package com.callor.score.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.score.model.ScoreVO;
import com.callor.score.service.ScoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/score")
public class ScoreController {
	
	protected final ScoreService scService;
	
	public ScoreController(ScoreService scService) {
		this.scService = scService;
	}
	
	@RequestMapping(value= {"/", ""}, method = RequestMethod.GET)
	public String score(Locale locale, Model model) {
		
		List<ScoreVO> scList = scService.showList();
		log.debug("scoreList 가져오기 : {}", scList);
		model.addAttribute("SCLIST", scList);
		
		return "score/list";
	}

}

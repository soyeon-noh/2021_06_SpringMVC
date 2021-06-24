package com.callor.score.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.score.model.ScoreDTO;
import com.callor.score.service.ScoreService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping(value="/score")
@Slf4j
@Controller
public class ScoreController {

	protected final ScoreService scService;
	
	public ScoreController(ScoreService scService) {
		this.scService = scService;
	}
	
	@RequestMapping(value={"/",""}, method=RequestMethod.GET)
	public String list(Locale locale, Model model) {
		
		List<ScoreDTO> scList = scService.selectViewAll();
		log.debug("Score selectViewAll {}", scList);
		model.addAttribute("SCORES", scList);
		
		model.addAttribute("BODY", "SCORE_VIEW");
		return "home";
		
	}
}

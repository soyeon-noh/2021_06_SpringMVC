package com.callor.score.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.score.model.HomeDTO;
import com.callor.score.service.HomeService;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	protected final HomeService homeService;
	
	public HomeController(HomeService homeService) {
		this.homeService = homeService;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		List<HomeDTO> homeList = homeService.showList();
		log.debug("homeList 가져오기: {}", homeList);
		model.addAttribute("HMLIST", homeList);
		
		return "home";
	}
	
}

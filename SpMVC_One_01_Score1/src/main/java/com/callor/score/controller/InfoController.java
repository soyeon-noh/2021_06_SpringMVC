package com.callor.score.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.score.model.HomeDTO;
import com.callor.score.service.HomeService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping(value = "/info")
@Slf4j
@Controller
public class InfoController {

	protected final HomeService homeService;
	
	public InfoController(HomeService homeService) {
		this.homeService = homeService;
	}
	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String info(String num, Locale locale, Model model) {
		
		List<HomeDTO> infoList = homeService.infoList(num);
		log.debug("infoList 가져오기: {}", infoList);
		model.addAttribute("SCLIST", infoList);
		
		HomeDTO total = homeService.findById(num);
		
		return "info/home";
	}
}

package com.callor.book.controller.notuse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@RequestMapping(value="/movie")
//@Controller
public class NaverMovieController {

	@RequestMapping(value= {"/",""}, method=RequestMethod.GET)
	public String home(Model model) {
		
//		model.addAttribute("pHolder", "영화 검색어"); // home.jsp에서 설정함 (view단)
		model.addAttribute("CAT", "MOVIE");
		return "home";
	}
}

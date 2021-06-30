package com.callor.book.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.book.model.BookDTO;
import com.callor.book.service.NaverService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value="/book")
@Controller
public class NaverBookController {

	@Qualifier("naverServiceV1")
	protected final NaverService<BookDTO> nBookService;
	
	
	@RequestMapping(value= {"/",""}, method=RequestMethod.GET)
	public String home(@RequestParam(name="search", 
			required = false, 
			defaultValue = "") String search, Model model) throws IOException, ParseException {
		
		model.addAttribute("pHolder","도서 검색어");
		if(search != null && !search.equals("")) { // 검색어가 널이아니고 검색어가 비어있지않으면
			// (null 체크는 혹시모를 exception을 위해서.. 사실 필요없음default값을 ""으로해서 ㅎㅎ
			
			String queryURL = nBookService.queryURL(search.trim()); //검색
			String jsonString = nBookService.getJsonString(queryURL); 
			
			List<BookDTO> bookList = nBookService.getNaverList(jsonString); //리스트를 만들어서
			model.addAttribute("BOOKS", bookList); //home.jsp에 보냄
		}
		return "home";
	}
	

}

package com.callor.book.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.callor.book.model.BookDTO;
import com.callor.book.service.NaverService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value="/naver")
@Controller
public class NaverController {
	
	protected final NaverService<BookDTO> nBookService;
	
	@ResponseBody
	@RequestMapping(value="/book", 
					method=RequestMethod.GET)
					// produces = "application/json;char=UTF8") // 화면창 한글설정.. 근데 List형식이면 필요없다고..?
					// return 할때 이런 타입으로 데이터를 보내겠다는 뜻
	// 내가 보낸건 문자열이 아니라 json이다! 그리고 한글이다! 라고 알려주는 것
	public List<BookDTO> book(String search) throws IOException, ParseException { 
		
		String queryURL =  nBookService.queryURL(search);
		String jsonString = nBookService.getJsonString(queryURL);
		List<BookDTO> bookList = nBookService.getNaverList(jsonString);
		return bookList;
	}
}

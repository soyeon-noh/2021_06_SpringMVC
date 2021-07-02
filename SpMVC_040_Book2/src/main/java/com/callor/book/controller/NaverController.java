package com.callor.book.controller;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.book.config.NaverQualifier;
import com.callor.book.service.impl.NaverMainService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RequestMapping(value="/naver")
@Controller
public class NaverController {
	
//	protected final BookService myBookService;
	
	@Qualifier(NaverQualifier.NAVER_MAIN_SERVICE_V1)
	protected final NaverMainService nService;
	/*
	 * web client에서 서버로 Request를 할때
	 * 어떤 데이터를 보내는 방법
	 * 
	 * ?변수=값 : GET method 방법으로 queryString으로 데이터 보내기
	 * ?username=callor&pw=12345
	 * (클라이언트에서 보내는 변수와 username과 pw라는.. .값이 같아야한다?) 
	 * 
	 * 2. request Body 에 담아보내는 방법
	 * <form method=POST><input username>
	 * 
	 * 3. url Path(Path Variable) 방식
	 * http://localhost:8080/book/naver/korea
	 * http://localhost:8080/book/naver/callor/12345
	 * (url 주소처럼 변수를 싵어 보내는방법. 
	 * 위의 callor와 12345를 각자 username과 password에 넣어서 사용가능)
	 * Mapping(value="/naver/{username}/{password}
	 * (클라이언트가 보내는 변수명 상관 x. 칸만 맞으면됨)
	 *
	 *	3번방법은 nod.js에서 먼저 11년도에 사용. Spring에서 사용할수있게된지 오래되지않았음.
	 */
	@RequestMapping(value="/{CAT}", method=RequestMethod.GET) //url카테고리에 CAT이라는 변수를..
	public String home(
			@PathVariable(name="CAT") String cat, // 초록 url은 변수다! 뭐가 담기면 보라 url에 넣어주라!
			@RequestParam(name = "search", required = false,
			defaultValue="") String search,
			Model model) throws IOException, ParseException {
		
		log.debug("URL {}", cat);
		model.addAttribute("CAT", cat);
		
//		List<BookDTO> myBookList = myBookService.selectAll();
//		model.addAttribute("MY_BOOKS", myBookList);
		
		nService.naverGetData(cat, search, model);
		return "home";
	}
}

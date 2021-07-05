package com.callor.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.callor.book.model.BookDTO;
import com.callor.book.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RequestMapping(value="/book")
@Controller
public class BookController {
	
	
	protected final BookService bookService;
	
	@RequestMapping(value= "/insert/{isbn}", method=RequestMethod.GET)
	public String insert(
			@PathVariable("isbn") String isbn, Model model) throws Exception {

		log.debug("ISBN : {}", isbn);
		
		int ret = bookService.insert(isbn);
		
		return "redirect:/book/list";
	}
	
	/* 새로운것! ModelAndView */
	/*
	 * 통상적인 Spring Framework의 method에서는
	 * 
	 * view 파일의 이름을 String 형으로 return하고 
	 * view에서 랜더링할 데이터는 
	 * Model 객체에 Attribute로 담아서 보내는 방식
	 * 
	 * ModelAndView 객체를 별도로 생성하여
	 * view는 setViewName() method를 이용하여 setting ㅏ고
	 * 랜더링 할 데이터는 addObject() meethod를 이용해 추가하고
	 * ModelAndView 객체를 return 하는 방식도 사용한다
	 */
	@RequestMapping(value= "/list", method=RequestMethod.GET)
	public ModelAndView list() { // 매개변수로 model빠짐
		
		// 스프링에서 model을 쓰지않는법 (한번정도 봐둬라)
		List<BookDTO> bookList = bookService.selectAll();
		ModelAndView mv = new ModelAndView(); 
		// 스프링에서 new사용은.. 스프링답지않아서 선호하지않지만 지금도 많이들 사용하는 코드라 한번 봐두래
		
		mv.setViewName("home");
		mv.addObject("MY_BOOKS", bookList);
		return mv;
		
		// model.addAttribute("BOOKS", books);
		//return "/book/list_view";
	}

	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String detail(String isbn) {
		
		// isbn을 받아서 도서 정보를 findById() 하고 
		// 자세히 보이기 화면 구현
		
		return "home";
	}
}

package com.callor.jdbc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.callor.jdbc.model.AuthorVO;
import com.callor.jdbc.model.BookVO;
import com.callor.jdbc.model.CompVO;
import com.callor.jdbc.service.AuthorService;
import com.callor.jdbc.service.BookService;
import com.callor.jdbc.service.CompService;
import com.callor.jdbc.service.HomeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * private final, protected final로 선언된
 * 필드 맴버 변수를 초기화하는 코드를 갖는
 * 생성자를 만들어 준다
 */
@Slf4j
@RequiredArgsConstructor
@Service("homeServiceV1")
public class HomeServiceImplV1 implements HomeService{

	protected final BookService bookService;
	protected final AuthorService auService;
	protected final CompService cpService;
	
	
	@Override
	public void dashBoard(Model model) {
		// TODO Auto-generated method stub
		List<BookVO> bookList = bookService.selectAll();
		List<AuthorVO> authorList = auService.selectAll();
		List<CompVO> compList = cpService.selectAll();
	
		
	/*
	 * model 객체는 Controller에서 사용하는 view 를 렌더링하는
	 * 데이터를 담는 도구이다.
	 * 
	 * 3개의 Dao로부터 데이터를 받아서 Controller로 return하려면
	 * 상당히 불편한 상황이 연출될 수 있다.
	 * 
	 * 이때,
	 * Controller로부터 model 객체를 매개변수로 받아서
	 * model 객체에 Attribute를 추가하면
	 * Controller의 원래 model에도 Attribute가 추가되는
	 * 효과를 낼 수 있다.
	 * 
	 * Call by Reference처럼 작동하는 
	 * 		Java의 객체 성질을 활용하기
	 */
		model.addAttribute("BOOKS", bookList);
		model.addAttribute("AUTHORS", authorList);
		log.debug("SERVICE COMP {}",compList);
		model.addAttribute("COMPS", compList);
	}

}

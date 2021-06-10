package com.callor.spring;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		// logger의 method 들
		// 이 method 들을 logger에서는 level이라고 부른다.
		
		// 아래 적혀있는 순서대로 무엇이 무시되는지 알 수 있다. 
		// 선택한 level 윗부분 method로 출력되는 건 다 무시된다.
		// 예)
		// logback-test.xml의 level을 info로 설정하면
		// 	코드 곳곳에 logger.trace(), logger.debug()로 
		// 	출력한 곳은 모두 코드가 무시된다.
		logger.trace("트레이스");
		logger.debug("디버그");
		logger.info("인포 Welcome home! The client locale is {}.", locale);
		logger.warn("워닝");
		logger.error("에러");
		
		logger.debug(String.valueOf(300*400));
		logger.debug("여기는 Home Controller return 바로위");
		return "home";
	}

}
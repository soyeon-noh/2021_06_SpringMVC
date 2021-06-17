package com.callor.jdbc.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.jdbc.service.RentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	// 시범코드
	// string.properties 파일에 설정된 속성값을 가져와서 변수에 setting
	@Value("${user.name}")
	protected String user_name;
	
	@Value("${user.email}")
	protected String user_email;
	
	/*
	 *보편적인 Spring에서 bean을 사용하는 코드
	 * @Autowired
	 * private BookDao bookdao
	 * 이 코드에서 메모리 leak(누수)현상이 보고되어
	 * 다음의 코드를 권장한다.
	 */
//	protected final BookDao bookDao; // Service 만드니까 필요없어짐

	protected final RentService rentService;
	public HomeController(RentService rentService) {
		this.rentService = rentService;

	}
	
	/*
	 * 사용자에게 Response를 할 때
	 * forward 방법과 redirect 방법이 있다.
	 * 
	 * forwarding은 
	 * service 등등에서 생성한(조회한) 데이터를
	 * *.jsp 파일과 Rendering 하여 사용자에게 HTML 코드로 전송한다.
	 * 
	 * service 등등에서 생성한 데이터는 
	 * Model 객체에 addAttribute() method를 사용하여 데이터를 만들고
	 * ...
	 * 
	 * class Spring___ {  // 랜더링하는 클래스 spring
	 * 		main() {
	 * 			HomeController hController = new HomeController();
	 *			Locale locale = new Locale();
	 *			Model model = new Model();	// 이친구한테 데이타를 추가한거랑 마찬가지다		
	 * 			String url = hController.home(local, model) // home method를 호출하면 ↖
	 * 
	 * 			if( !url.comtains("redirect") ) {
	 * 				rendering(url, model);
	 * 			} 
	 * 		}
	 * }
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		// home이라는 method는 spring container가 호출하는 거래
		// requestMapping을 보고 !
		// 매개변수로 받고싶다고 local이랑 model 선언했는데
		// spring container가 넣어준대
		
		
		/*
		 * 매개변수로 전달받은 Model class type변수인 model에
		 * 속성을 하나 추가한다.
		 * 속성의 이름은 user이며, 값은 user_name에 담긴 값이다.
		 * 
		 * Model 객체에 담긴 속성(변수)들은 jsp파일과 Rendering이 완료되면
		 * 메모리에서 삭제된다.
		 */
		model.addAttribute("user",user_name);
		
		rentService.viewBookAndComp();
		return "home";
	}
	
}

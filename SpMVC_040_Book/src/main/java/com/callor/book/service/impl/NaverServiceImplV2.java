package com.callor.book.service.impl;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.callor.book.model.BookDTO;
import com.google.gson.Gson;
import com.google.gson.JsonElement; // 이 Json 이어야한다. 구분잘하기!
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

/*
 * ServiceV1을 상속받은 ServiceV2
 * Service interface와 ServiceV1에 구현된 method 코드를
 * 모두 그대로 상속받는다.
 * 
 * Service의 method 
 * 		queryURL(), getJsonString(), getNaverList() 중에서
 * 		getNaverList() method를 재정의 하려고 한다.
 * 
 * queryURL(), getJsonString() method는 그대로 상속받아 사용하고
 * getNaverList() 만 다시 작성하겠다.
 * 
 * ServiceV1의 getNaverList()는 json-simple을 사용하여 
 * JSON parsing을 수행한 후 List를 return 하는 코드. 
 *		-> 파싱하고 vo에 담고.. 리스트에담고... (번거롭고 안드로이드에서쓰는법)
 * 
 * ServiceV2에서는 getNaverList() method를 다시 작성하여
 * gson을 사용하여 JSON parsing을 수행하는 코드로 작성하기
 * 		-> 이걸로 해보겠다!
 */
@Slf4j
@Service("naverServiceV2") // 이름을 안붙여주면  NoUniqueBeanDefinitionException 발생
public class NaverServiceImplV2 extends NaverServiceImplV1{@Override

	// gSon을 사용하여 jsonString 을 parsing 하기
	public List<BookDTO> getNaverList(String jsonString) throws ParseException {
	
		log.debug("나는 ServiceV2 ");
		log.debug(" n n ");
		log.debug("( '^') ");
	
		// 문자열형 JSON 인 jSonString을 Json 객체로 변환하기
		JsonElement jSonElement = JsonParser.parseString(jsonString);
		
		// 필요한 항목만 가져오기
		JsonElement oItems = jSonElement
				.getAsJsonObject() // 오브젝트를 추출하고
				.get("items"); // 그중에서 items를 추출..? 해서 대입
		
		Gson gson = new Gson();
		
		/*
		 * List는 interface인데 interface는
		 * 자신의 type을 가지고 있지않는 객체이다.
		 * 
		 * 때문에 Gson을 이용하여 JSON parsing을 할 경우
		 * List<DTO> 구조를 알수있는 방법이 없어서
		 * 
		 * TypeToken을 통해
		 * Gson 특별한 객체 생성자를 하나 제공해 주고
		 * 이 객체를 통하여 List<DTO>의 구조를 알 수 있도록
		 * 만들어준다.
		 */
		TypeToken<List<BookDTO>> typeToken 
				= new TypeToken<List<BookDTO>>() {} ;
			
		List<BookDTO> bookList
			= gson.fromJson(oItems, typeToken.getType()); 
			// jSonElement와 Type을 get해서 넣어주면 알아서 뽑아준대
			// 이 기능대문에 simple json쓰다가 gson으로 넘어왔대
		
		return bookList;
	}

	
	
	
	
	
	
	
	
}

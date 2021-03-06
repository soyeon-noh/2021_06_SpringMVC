package com.callor.book.service.impl.books;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.callor.book.config.NaverQualifier;
import com.callor.book.config.NaverSecret;
import com.callor.book.model.BookDTO;
import com.callor.book.service.NaverAbstractService;

import lombok.extern.slf4j.Slf4j;

/*
 * NaverAbstractService 추상클래스를 상속받아 구현(된) 클래스
 * 추상클래스에 사전 정의된 jsonString() method 코드는
 * 직접 작성하지 않고 사용할 수 있다.
 * 		jsonString()
 * 
 * 추상method는 반드시 구현해야 한다.
 *  	queryURl(), getNaverList()
 * 
 * 다음과 같은 형식으로 사용가능하다.
 * 		NaverAbstractService nService 
 * 			= new NaverServiceImplV1()
 * 		nService.queryURL()
 * 		nService.jsonString()
 * 		nService.getNaverList()
 * 
 */

@Slf4j
@Service(NaverQualifier.NAVER_BOOK_SERVICE_V1) // 붙여주지않으면 V2와 함께 NoUniqueBeanDefinitionException 발생
public class NaverBookServiceImplV1 extends NaverAbstractService<BookDTO>{
	/*
	 * naver에 요청하기
	 * BookURL + "?query=" + 검색문자열
	 */
	public String queryURL(String search) {
		
		String searchUTF8 = null; // try문이 끝난다음에도 쓸 수 있도록 밖으로 뺌
		
		// 검색하고자 하는 문자열을 UTF-8로 인코딩
		try {
			searchUTF8 = URLEncoder.encode(search, "UTF-8"); //엔코딩함
			// 옛날과 달리 이제 인코딩 타입을 명시해야함 ("UTF-8")
		} catch (UnsupportedEncodingException e) { //try-catch 사용
			// TODO UTF-8로 바꿀 수가 없다! 라는 Exception
			e.printStackTrace();
		} 
					
		StringBuilder queryURL = new StringBuilder(); // StrtingBuilder를 써봤다.
		queryURL.append(NaverSecret.NURL.BOOK); 
	
		// queryString += BookURL 과 같다..(요새는 이런거 써도 되는데 옛날새럼들이 이렇게한대)
		
		String queryString = String.format("?query=%s", searchUTF8); // query = 검색문자열
		queryURL.append(queryString);
		
		queryString = String.format("&display=%d", 20);
		queryURL.append(queryString);
		
		log.debug("queryURL {}", queryURL.toString());
		return queryURL.toString(); // 다시 문자열로 만들어야하기떄문에 문자열화함
	}



	/*
	 * 네이버에서 받은 JSonString을 parsing하여
	 * List<BookDTO>에 담아서 return
	 * 
	 * json-simple을 사용하여 parsing하기
	 */ 
	@Override
	public List<BookDTO> getNaverList(String jsonString) throws Exception {

		log.debug("나는 ServiceV1 ");
		log.debug("  n n ");
		log.debug("('^ ') ");
		
		// 1. json Parsing 도구 선언 (import는 google이래)
		JSONParser jParser = new JSONParser();
		
			// JsonString을 JSON 객체로 변환
			JSONObject jObject = (JSONObject) jParser.parse(jsonString);
			
			// parsing된 JSON 객체에서 items 항목을 추출하여
			// JSON 배열 타입으로 변환하기( 내부적으로는 List 타입)
			JSONArray items = (JSONArray) jObject.get("items");
			
			List<BookDTO> bookList = new ArrayList<BookDTO>();
			int nSize = items.size();
			for(int i = 0; i < nSize; i++) {
				
				// 한권의 도서정보가 담긴 객체 추출
				JSONObject item = (JSONObject) items.get(i);
				
				// 도서정보 항목을 문자열 변수에 저장
				String title = item.get("title").toString();	//	string	검색 결과 문서의 제목을 나타낸다. 제목에서 검색어와 일치하는 부분은 태그로 감싸져 있다.
				String link =  item.get("link").toString();	//	string	검색 결과 문서의 하이퍼텍스트 link를 나타낸다.
				String image = item.get("image").toString();	//	string	썸네일 이미지의 URL이다. 이미지가 있는 경우만 나타납난다.
				String author = (String)item.get("author");	//	string	저자 정보이다.
				String price = (String)item.get("price");	//	integer	정가 정보이다. 절판도서 등으로 가격이 없으면 나타나지 않는다.
				String discount = (String)item.get("discount");//	integer	할인 가격 정보이다. 절판도서 등으로 가격이 없으면 나타나지 않는다.
				String publisher = (String)item.get("publisher");//	string	출판사 정보이다.
				String isbn = (String)item.get("isbn");	//	integer	ISBN 넘버이다.
				String description = (String)item.get("description");//	string	검색 결과 문서의 내용을 요약한 패시지 정보이다. 문서 전체의 내용은 link를 따라가면 읽을 수 있다. 패시지에서 검색어와 일치하는 부분은 태그로 감싸져 있다.
				String pubdate = (String)item.get("pubdate");
				
				// BookDTO에 담기
				BookDTO bookDTO = BookDTO.builder()
				.title(title)
				.link (link)
				.image (image)
				.author (author)
				.price(price)
				.discount(discount)
				.publisher(publisher)
				.isbn(isbn)
				.description(description)
				.pubdate(pubdate)
				.build();
			// List에 add하기
			bookList.add(bookDTO);
			}	
			return bookList;
	}
}

package com.callor.book.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.callor.book.config.NaverSecret;
import com.callor.book.model.BookDTO;
import com.callor.book.service.NaverService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NaverServiceImplV1 implements NaverService<BookDTO>{

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
	 * queryURL을 naver 에 전송하고 naver로 부터 결과를 받는 method
	 */
	@Override
	public String getJsonString(String queryURL) throws IOException {

		// API를 통하여 다른 서버에 Request를 보낼때 사용할 객체
		URL url = null;
		
		// Http 프로콜을 통하여 다른 서버에 연결할때 사용할 객체
		HttpURLConnection httpConn = null;
		
			// queryURL 주소를 Request 정보로 변환
			url = new URL(queryURL);
			
			//  생성된 URL 정보를 사용하여 다른 서버에 연결
			httpConn = (HttpURLConnection) url.openConnection();
			
			// 요청하는 method를 GET 으로 설정하기
			httpConn.setRequestMethod("GET");
			
			httpConn.setRequestProperty("X-Naver-Client-Id", 
					NaverSecret.NAVER_CLIENT_ID);
			httpConn.setRequestProperty("X-Naver-Client-Secret", 
					NaverSecret.NAVER_CLIENT_SECRET);
			
			
			// naver 가 어떤 응답을 할 것인지 
			// 		미리 확인하는 코드를 요청한다.
			int httpStatusCode = httpConn.getResponseCode();
			
			// naver로 부터 데이터를 수신할 객체
			InputStreamReader is = null;
			
			if(httpStatusCode == 200) {
				is = new InputStreamReader(httpConn.getInputStream()); 
				// 연결통로를 요청하고 InputStreamReader에 파이프연결
			} else {
				is = new InputStreamReader(httpConn.getErrorStream()); // 오류가발생했을떄
			}
			
			// is를 buffer에 연결
			BufferedReader buffer = null;
			buffer = new BufferedReader(is);
			
			/*
			 * StringBuilder, StringBuffer
			 * 
			 * String 형의 데이터를 += 처럼
			 * 사용할 떄 발생하는 메모리 leak, 성능저하 문제를 
			 * 해결하기 위하여 탄생된 클래스
			 * 
			 * String 형의 데이터를 += 하면
			 * 예) 다음과 같은 코드를 반복하면
			 * 		String str = "대한민국"
			 * 		str += "Korea"  // 원래있던 str 제거하고 str을 새로만들고 두문자열이 합쳐진 값을 담는 것임.
			 * 		str += "Republic"
			 * 
			 * 내부적으로는 str 변수를 생성, 제거, 생성, 제거, 생성
			 * 하는 코드가 반복적으로 수행된다.
			 * 
			 * 이러한 현상이 반복되면 메모리에 문제가 발생할 수 있다.
			 * 
			 * 그러한 문제를 해결하기 위하여 탄생한 클래스다.
			 * 
			 * 겉으로 보기에는 두 클래스의 역할, 사용법이 똑같다.
			 * 
			 * StringBuilder는 Single Thread에서 최적화 되어 있다.
			 * StringBuffer는 Multi Thread에서 safe 하다
			 * 
			 * (상황에 따라 적절한 것을 사용하다. StringBuilder가 조금 더 빠르다고 한다.
			 */
			
			StringBuffer sBuffer = new StringBuffer();
			
			// 가져온 데이터를 읽어서 변수에 담기
			while(true){
				
				String reader = buffer.readLine();
				if(reader == null) {
					break;
				}
				sBuffer.append(reader); // 추가시키기
			}
			return sBuffer.toString();

	}

	/*
	 * 네이버에서 받은 JSonString을 parsing하여
	 * List<BookDTO>에 담아서 return
	 * 
	 * json-simple을 사용하여 parsing하기
	 */ 
	@Override
	public List<BookDTO> getNaverList(String jsonString) throws ParseException {

		// 1. json Parsing 도구 선언
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

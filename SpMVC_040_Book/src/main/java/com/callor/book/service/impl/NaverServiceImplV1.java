package com.callor.book.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

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
	public String getJsonString(String queryURL) {

		// API를 통하여 다른 서버에 Request를 보낼때 사용할 객체
		URL url = null;
		
		// Http 프로콜을 통하여 다른 서버에 연결할때 사용할 객체
		HttpURLConnection httpConn = null;
		
		try {
			// queryURL 주소를 Request 정보로 변환
			url = new URL(queryURL);
			
			//  생성된 URL 정보를 사용하여 다른 서버에 연결
			httpConn = (HttpURLConnection) url.openConnection();
			
			
			// naver 가 어떤 응답을 할 것인지 미리 확인하는 코드를 요청한다.
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
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public List<BookDTO> getNaverList(String jsonString) {
		// TODO Auto-generated method stub
		return null;
	}
}

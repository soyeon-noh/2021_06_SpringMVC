package com.callor.book.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.callor.book.config.NaverSecret;

/*
 * abstract 키워드를 추가함으로써 추상클래스가 된다.
 */
public abstract class NaverAbstractService<T> { // 생성은 일반 클래스와 동일. abstract 키워드 추가.

	public abstract String queryURL(String search);
	public String jsonString(String queryURL) throws IOException {
		
		URL url = new URL(queryURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		
		httpConn.setRequestMethod("GET");
		httpConn.setRequestProperty("X-Naver-Client-Id", 
				NaverSecret.NAVER_CLIENT_ID);
		httpConn.setRequestProperty("X-Naver-Client-Secret", 
				NaverSecret.NAVER_CLIENT_SECRET);
			
			
		int httpStatusCode = httpConn.getResponseCode();
			
		InputStreamReader is = null;
			
		if(httpStatusCode == 200) {
			is = new InputStreamReader(httpConn.getInputStream()); 
		} else {
			is = new InputStreamReader(httpConn.getErrorStream()); // 오류가발생했을떄
		}
			
		BufferedReader buffer = null;
		buffer = new BufferedReader(is);
			
		StringBuffer sBuffer = new StringBuffer();
			
		while(true){
			String reader = buffer.readLine();
			if(reader == null) {
				break;
			}
			sBuffer.append(reader); // 추가시키기
		}
		return sBuffer.toString();
	
	}
	public abstract List<T> getNaverList(String jsonString); 
}

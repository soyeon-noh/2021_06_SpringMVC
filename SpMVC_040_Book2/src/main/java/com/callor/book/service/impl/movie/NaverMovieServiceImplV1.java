package com.callor.book.service.impl.movie;

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
import com.callor.book.model.MovieDTO;
import com.callor.book.service.NaverMovieService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("naverMovieServiceV1")
public class NaverMovieServiceImplV1 implements NaverMovieService {

	@Override
	public String queryURL(String search_text) {

		// 검색 문자열을 UTF-8로 인코딩하기
		String searchUTF8 = null;
		try {
			searchUTF8 = URLEncoder.encode(search_text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 요청변수 셋팅해서 return

		StringBuilder queryURL = new StringBuilder(); // StrtingBuilder를 써봤다.
		queryURL.append(NaverSecret.NURL.MOVIE); 
	
		// queryString += BookURL 과 같다..(요새는 이런거 써도 되는데 옛날새럼들이 이렇게한대)
		
		String queryString = String.format("?query=%s", searchUTF8); // query = 검색문자열
		queryURL.append(queryString);
		
		queryString = String.format("&display=%d", 20);
		queryURL.append(queryString);
		
//		String queryURL = NaverSecret.NURL.MOVIE;
//		String queryString = String.format("?query=%s", searchUTF8);
//		queryString += String.format("&display=%d", 20);
//		queryURL += queryString;

		log.debug("queryURL {}", queryURL.toString());
		return queryURL.toString();
	}

	@Override
	public String getJsonString(String queryURL) throws IOException {
		// TODO 모든 검색 Service 는 다 같은 method를 쓴다
		URL url = null;

		HttpURLConnection httpConn = null;

		url = new URL(queryURL);
		httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("GET");
		httpConn.setRequestProperty("X-Naver-Client-Id", NaverSecret.NAVER_CLIENT_ID);
		httpConn.setRequestProperty("X-Naver-Client-Secret", NaverSecret.NAVER_CLIENT_SECRET);

		int httpStatusCode = httpConn.getResponseCode();
		InputStreamReader is = null;

		if (httpStatusCode == 200) {
			is = new InputStreamReader(httpConn.getInputStream());
		} else {
			is = new InputStreamReader(httpConn.getErrorStream());
		}

		BufferedReader buffer = null;
		buffer = new BufferedReader(is);

		StringBuffer sBuffer = new StringBuffer();

		while (true) {
			String reader = buffer.readLine();
			if (reader == null) {
				break;
			}
			sBuffer.append(reader);
		}
		return sBuffer.toString();
	}

	@Override
	public List<MovieDTO> getNaverList(String jsonString) throws ParseException {

		log.debug("나는... 뭘까... ");
		
		JSONParser jParser = new JSONParser();
		JSONObject jObject = (JSONObject) jParser.parse(jsonString);

		JSONArray items = (JSONArray) jObject.get("items");

		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
		int nSize = items.size();
		for (int i = 0; i < nSize; i++) {

			JSONObject item = (JSONObject) items.get(i);

			String title = item.get("title").toString(); // string 검색 결과 영화의 제목을 나타낸다. 제목에서 검색어와 일치하는 부분은 태그로 감싸져 있다.
			String link = item.get("link").toString(); // string 검색 결과 영화의 하이퍼텍스트 link를 나타낸다.
			String image = item.get("image").toString(); // string 검색 결과 영화의 썸네일 이미지의 URL이다. 이미지가 있는 경우만 나타난다.
			String subtitle = (String) item.get("subtitle"); // string 검색 결과 영화의 영문 제목이다.
			String pubDate = (String) item.get("pubDate"); // date 검색 결과 영화의 제작년도이다.
			String director = (String) item.get("director"); // string 검색 결과 영화의 감독이다.
			String actor = (String) item.get("actor"); // string 검색 결과 영화의 출연 배우이다.
			String userRating = (String) item.get("userRating"); // integer 검색 결과 영화에 대한 유저들의 평점이다.

			MovieDTO movieDTO = MovieDTO.builder().title(title).link(link).image(image).subtitle(subtitle)
					.pubDate(pubDate).director(director).actor(actor).userRating(userRating).build();
			movieList.add(movieDTO);

		}
		
		return movieList;
	}

}

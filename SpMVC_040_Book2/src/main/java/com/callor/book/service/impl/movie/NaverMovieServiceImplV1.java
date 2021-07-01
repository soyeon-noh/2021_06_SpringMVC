package com.callor.book.service.impl.movie;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.callor.book.config.NaverQualifier;
import com.callor.book.config.NaverSecret;
import com.callor.book.model.MovieDTO;
import com.callor.book.model.NewsDTO;
import com.callor.book.service.NaverAbstractService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(NaverQualifier.NAVER_MOVIE_SERVICE_V1)
public class NaverMovieServiceImplV1 extends NaverAbstractService<MovieDTO> {

	@Override
	public String queryURL(String search_text) throws UnsupportedEncodingException {

		// 검색 문자열을 UTF-8로 인코딩하기
		String searchUTF8 = URLEncoder.encode(search_text, "UTF-8");
		
		//선생님코드
		String queryURL = NaverSecret.NURL.MOVIE;
		queryURL += "?query=%s&display=10";
		
		queryURL = String.format(queryURL, searchUTF8);
		log.debug("queryURL : {}", queryURL);
		
		return queryURL;
	
	}

	/*
	 * gSon 을 사용하여 jsonString을 List<MovieDTO>로 변환하기
	 */
	@Override
	public List<MovieDTO> getNaverList(String jsonString) throws ParseException {

		JsonElement jsonElement
			= JsonParser.parseString(jsonString); // 구글 gson의 JsonParser!
		JsonElement oItems 
			= jsonElement.getAsJsonObject().get("items");
		
		Gson gson = new Gson(); // gson을 사용하여
		TypeToken<List<MovieDTO>> typeToken 
			= new TypeToken<List<MovieDTO>>() {};
			
		List<MovieDTO> movies
			= gson.fromJson(oItems,typeToken.getType()); // fromJson
		return movies;		
		
//		log.debug("나는... 뭘까... ");
//		
//		JSONParser jParser = new JSONParser();
//		JSONObject jObject = (JSONObject) jParser.parse(jsonString);
//
//		JSONArray items = (JSONArray) jObject.get("items");
//
//		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
//		int nSize = items.size();
//		for (int i = 0; i < nSize; i++) {
//
//			JSONObject item = (JSONObject) items.get(i);
//
//			String title = item.get("title").toString(); // string 검색 결과 영화의 제목을 나타낸다. 제목에서 검색어와 일치하는 부분은 태그로 감싸져 있다.
//			String link = item.get("link").toString(); // string 검색 결과 영화의 하이퍼텍스트 link를 나타낸다.
//			String image = item.get("image").toString(); // string 검색 결과 영화의 썸네일 이미지의 URL이다. 이미지가 있는 경우만 나타난다.
//			String subtitle = (String) item.get("subtitle"); // string 검색 결과 영화의 영문 제목이다.
//			String pubDate = (String) item.get("pubDate"); // date 검색 결과 영화의 제작년도이다.
//			String director = (String) item.get("director"); // string 검색 결과 영화의 감독이다.
//			String actor = (String) item.get("actor"); // string 검색 결과 영화의 출연 배우이다.
//			String userRating = (String) item.get("userRating"); // integer 검색 결과 영화에 대한 유저들의 평점이다.
//
//			MovieDTO movieDTO = MovieDTO.builder().title(title).link(link).image(image).subtitle(subtitle)
//					.pubDate(pubDate).director(director).actor(actor).userRating(userRating).build();
//			movieList.add(movieDTO);

//		}
//		
//		return movieList;
	}

}

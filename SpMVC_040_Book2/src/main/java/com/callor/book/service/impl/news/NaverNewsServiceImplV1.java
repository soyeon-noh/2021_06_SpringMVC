package com.callor.book.service.impl.news;

import java.net.URLEncoder;
import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.book.config.NaverQualifier;
import com.callor.book.config.NaverSecret;
import com.callor.book.model.NewsDTO;
import com.callor.book.service.NaverAbstractService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

@Service(NaverQualifier.NAVER_NEWS_SERVICE_V1)
public class NaverNewsServiceImplV1 extends NaverAbstractService<NewsDTO>{

	@Override
	public String queryURL(String search) throws Exception {

		// 검색 문자열을 UTF-8로 인코딩하기
		String searchUTF8 = URLEncoder.encode(search, "UTF-8");
		
		// URL query 셋팅
		String queryURL = NaverSecret.NURL.NEWS;
		queryURL += "?query=%s&display=10";
		
		// format
		queryURL = String.format(queryURL, searchUTF8);
		
		return queryURL;
	}

	/*
	 * gSon 을 사용하여 jsonString을 List<MovieDTO>로 변환하기
	 */
	@Override
	public List<NewsDTO> getNaverList(String jsonString) throws Exception {
		
		JsonElement jsonElement
			= JsonParser.parseString(jsonString); // 구글 gson의 JsonParser!
		JsonElement oItems 
			= jsonElement.getAsJsonObject().get("items");
		
		Gson gson = new Gson(); // gson을 사용하여
		TypeToken<List<NewsDTO>> typeToken 
			= new TypeToken<List<NewsDTO>>() {};
			
		List<NewsDTO> newsList
			= gson.fromJson(oItems,typeToken.getType()); // fromJson
		return newsList;
			
	}

}

package com.callor.book.service.impl;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.callor.book.model.BookDTO;
import com.callor.book.model.MovieDTO;
import com.callor.book.service.NaverBookService;
import com.callor.book.service.NaverMovieService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("naverMainServiceV1")
public class NaverMainServiceImpl {

	@Qualifier("naverBookServiceV2")
	protected final NaverBookService nBookService;
	@Qualifier("naverMovieServiceV1")
	protected final NaverMovieService nMovieService;
	
	public void naverGetData(String cat, String search, Model model) throws IOException, ParseException {
		if(search != null && !search.equals("")) { //서치값이 있으면
			if(cat.equalsIgnoreCase("BOOK")) { //카테고리가 BOOK이면
				//도서 검색 서비스
				String queryURL = nBookService.queryURL(search);
				String jsonString = nBookService.getJsonString(queryURL);
				List<BookDTO> books = nBookService.getNaverList(jsonString);
				model.addAttribute("BOOKS", books); // model에 BOOKS 데이터를 담아라
				
			}else if(cat.equalsIgnoreCase("NEWS")) {
				// 뉴스 검색 서비스

				
			}else if(cat.equalsIgnoreCase("MOVIE")) {
				//영화 검색 서비스
				String queryURL = nMovieService.queryURL(search);
				String jsonString = nMovieService.getJsonString(queryURL);
				List<MovieDTO> movies = nMovieService.getNaverList(jsonString);
				model.addAttribute("MOVIES", movies);
			}
		}
	}
}

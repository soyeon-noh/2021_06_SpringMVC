package com.callor.gallery.service.impl;

import org.springframework.stereotype.Service;

import com.callor.gallery.model.PageDTO;
import com.callor.gallery.service.PageService;

import lombok.Setter;

@Setter // 게시판별로 페이지수를 설정할 수 있게 setter를 붙이자.
@Service
public class PageServiceImplV1 implements PageService {
	
	// 한 페이지에 보여질 데이터 리스트의 개수
	protected int listPerPage = 10;
	// 한 페이지에 보여질 페이지 네비의 개수 
	protected int navsPerPage = 5;
	
	/*
	 * 전체 페이지수와현재 페이지번호를 매개변수로 받아서
	 * pagination을 그리는데 필요한 데이터를 생성하기
	 */
	@Override
	public PageDTO makePagination(int totalListSize, int currentPage) {
		// TODO Auto-generated method stub
		
		if(totalListSize < 1 ) return null;
		
		// 이 연산을 수행하는 전체 리스트를 표현하는데 몇페이지가 필요한가를 계산
		// 이 연산은 int 형 데이터로 연산을 수행하므로 소수점이하를 무조건 자른다.
		// 마지막 페이지 개수가 listPerPage 보다 작으면 마지막 페이지는 무시하는
		// totalPages 값이 계산된다.
		
		// 실수형 값을 소수점 이하에서 반올림 하여 결과를 만드는 함수
		// Math.round() : 반올림
		// Math.floor() : 소수점이하 무조건 버림
		// Math.ceil() : 소수점이하 무조건 올림
		
		int totalPages = (int)Math.round((double)totalListSize / (double)this.listPerPage); 
		
		int startPage = currentPage - (this.navsPerPage/2);
		// 내비 시작페이지를 계산하여 1보다 작으면 무조건 1부터 시작
		startPage = startPage < 1 ? 1 : startPage;
		
		int endPage = startPage + this.navsPerPage - 1;
		// 마지막 페이지가 전체 페이지 수보다 커지면 마지막 페이지로 세팅하기
		endPage = endPage > totalPages ? totalPages : endPage;
		
		// 데이터를 자르기 위한 변수
		// 몇번데이터부터 몇번째까지 자를지 선택하기
		
		// 전체 데이터가 685개라고 가정.
		// 마지막 페이지를 표현하기 위한 offset과 limit 는 
		// 		offset : 680, limit : 5가 되어야 하는데 
		// 		아래 연산에서 limit은 690이 되어버린다!
		// 		그러면 데이터를 자르는 과정에서 exception이 발생할 것이다...
		int offset = (currentPage - 1) * this.listPerPage;
		int limit = offset + this.listPerPage; 
				
		// 마지막 위치 값이 전체 리스트보다 크면
		// 		전체 리스트 끝 값으로 세팅하기
		limit = limit > totalListSize ? totalListSize : limit; 
		
		// offset : 680, limit : 685가 되도록 만들었다.
		
		PageDTO pageDTO = PageDTO.builder()
				.totalPages(totalPages)
				.startPage(startPage)
				.endPage(endPage)
				.offset(offset)
				.limit(limit)
				.build();
		
		return pageDTO;
	}

}

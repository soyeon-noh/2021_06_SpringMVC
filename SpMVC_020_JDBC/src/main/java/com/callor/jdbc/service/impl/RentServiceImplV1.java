package com.callor.jdbc.service.impl;

import org.springframework.stereotype.Service;

import com.callor.jdbc.pesistance.AuthorDao;
import com.callor.jdbc.pesistance.BookDao;
import com.callor.jdbc.pesistance.CompDao;
import com.callor.jdbc.service.RentService;

@Service("rentV1")
public class RentServiceImplV1 implements RentService{

	protected final BookDao bookDao;
	protected final CompDao compDao;
	protected final AuthorDao authorDao;
	//여기서 초기화시켜준다. BookDao와 CompDao를 사용할 수 있게 인젝션한 것이다
	public RentServiceImplV1(BookDao bookDao, CompDao compDao, AuthorDao authorDao) { 
		
		this.bookDao = bookDao;
		this.compDao = compDao;
		this.authorDao = authorDao;
	}
	
	@Override
	public void viewBookAndComp() {
		// TODO Auto-generated method stub
		bookDao.selectAll();
		compDao.selectAll();
		authorDao.selectAll();
		
	}

}

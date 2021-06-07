package com.callor.jdbc.pesistance.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.callor.jdbc.model.BookVO;
import com.callor.jdbc.pesistance.BookDao;

public class BookDaoImplV1 implements BookDao{

	// jdbc-context.xml에 선언된 jdbcTempate bean 사용하기
	protected final JdbcTemplate jdbcTemplate;
	public BookDaoImplV1(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}// 이렇게하면 우리가 jdbc를 사용할 준비가 된다.
	
	
	@Override
	public List<BookVO> selectAll() {
		// TODO Auto-generated method stub
		String sql = " SELECT * FROM tbl_books ";
		
		
		List<BookVO> books = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<BookVO>(BookVO.class) 
				// 내가 받는 data가 VO형태이니 그걸 받아서 내가 적은 위치에 담아달라. 그리고 List에도 담아달라.
				// ( 이전엔 sql문 넣고 결과문 받고 rSet에 담아서...  VO에 담고.. List에 담았었음 )
		);
		return null;
	}

	@Override
	public BookVO findById(String pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BookVO VO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BookVO VO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BookVO> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookVO> findByDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookVO> findByComp(String comp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookVO> findByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

}

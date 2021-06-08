package com.callor.jdbc.pesistance.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callor.jdbc.model.AuthorVO;
import com.callor.jdbc.pesistance.AuthorDao;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Repository("authorDaoV1") // 변수, 객체 이름 같이 첫글자 소문자로
public class AuthorDaoImplV1 implements AuthorDao{

	protected final JdbcTemplate jdbcTemplate;
	public AuthorDaoImplV1(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<AuthorVO> selectAll() {
		// TODO Auto-generated method stub
		String sql = " SELECT * FROM tbl_author ";
		List<AuthorVO> authorList = jdbcTemplate.query(sql, 
				new BeanPropertyRowMapper<AuthorVO>(AuthorVO.class)
				);
		log.debug("Author SELECT {}", authorList.toString());
		return null;
	}

	@Override
	public AuthorVO findById(String pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(AuthorVO VO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(AuthorVO VO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AuthorVO> findByAName(String aname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthorVO> findByATel(String atel) {
		// TODO Auto-generated method stub
		return null;
	}

}

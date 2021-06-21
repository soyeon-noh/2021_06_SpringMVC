package com.callor.jdbc.pesistance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callor.jdbc.model.AuthorVO;
import com.callor.jdbc.pesistance.AuthorDao;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Repository("authorDaoV1") // 변수, 객체 이름 같이 첫글자 소문자로
public class AuthorDaoImplV1 implements AuthorDao{

	/*
	 * 일반적인 Spring Framework에서 다른 bean을 연결하기
	 * @Autowired
	 * 		이미 bean으로 생성되어 Spring Container에 보관중인 객체를
	 * 		여기에 주입해 달라
	 * 
	 * @Inject 
	 * 		Java에서 기본적으로 제공하는 DI(Dependency Inject)를 수행하는 Annotaion
	 * 		일부에서 @Inject를 사용하자라는 말이 있지만
	 * 		SpringFramework를 사용할때는 굳이 그러지 말자!!
	 * 
	 */
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	// 메모리 누수가 있다는 말이 있어서 아래처럼 생성자를 만들어서 쓰기로한다
	// 4~5년전코드
	
//	protected final JdbcTemplate jdbcTemplate;
//	
//	public AuthorDaoImplV1(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
	// 이런 생성자만드는 게 최신코드
	
	// 생성자 코드를 도와주는게 @RequiredArgsConstructor -> 설명은 HomeServiceimplV1
	
	@Override
	public List<AuthorVO> selectAll() {

		String sql = " SELECT * FROM tbl_author ";
		List<AuthorVO> authorList 
			= jdbcTemplate.query(sql, 
				new BeanPropertyRowMapper<AuthorVO>(AuthorVO.class));
		
		log.debug("Author SELECT {}", authorList.toString());
		return authorList;
	}

	@Override
	public AuthorVO findById(String au_code) {
		
		String sql = " SELECT * FROM tbl_author ";
		sql += " WHERE cp_code = ?";

		AuthorVO author 
		= (AuthorVO) jdbcTemplate.query(sql, new Object[] {au_code},
				new BeanPropertyRowMapper<AuthorVO>(AuthorVO.class));
		return author;
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
		String sql = " SELECT * FROM tbl_author ";
		sql += " WHERE cp_title LIKE CONCAT('%', ? , '%') ";

		List<AuthorVO> authorList 
		=  jdbcTemplate.query(sql, new Object[] {aname},
				new BeanPropertyRowMapper<AuthorVO>(AuthorVO.class));
		return authorList;
	}

	@Override
	public List<AuthorVO> findByATel(String atel) {
		String sql = " SELECT * FROM tbl_author ";
		sql += " WHERE cp_tel =  ? ";

		/*
		 * 전화번호로 조회를 하면 1개의 데이터만 추출될 것이다
		 * 하지만 DB조회에서 PK를 기준으로 조회하는 경우를 제외하고는
		 * 모두 List type으로 데이터가 추출된다고 생각해야 한다.
		 */
		List<AuthorVO> authorList 
		=  jdbcTemplate.query(sql, new Object[] {atel},
				new BeanPropertyRowMapper<AuthorVO>(AuthorVO.class));
		return authorList;
	}

}

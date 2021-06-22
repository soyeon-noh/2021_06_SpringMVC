package com.callor.jdbc.pesistance.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callor.jdbc.model.BookVO;
import com.callor.jdbc.pesistance.BookDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("bookDaoV1")
public class BookDaoImplV1 implements BookDao{

// 아래는 Console로 log를 찍기 위하여 log 객체를 생성하는 코드
// 하지만 우리는 lombok으로 편하게 할 것이다. (일단 여기서는 slf4j짧은 것들로 가져옴)
// lombok @Slf4j 를 사용하여 아래 코드를 대신한다.
//	private static Logger log = LoggerFactory.getLogger("SERVICE");
	
	// jdbc-context.xml에 선언된 jdbcTempate bean 사용하기
	protected final JdbcTemplate jdbcTemplate;
	public BookDaoImplV1(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}// 이렇게하면 우리가 jdbc를 사용할 준비가 된다.
	
	
	@Override
	public List<BookVO> selectAll() {
		// TODO Auto-generated method stub
		String sql = " SELECT ";
		sql += " bk_isbn,";
		sql += " bk_title,";
		sql += " C.cp_title as bk_ccode ,";
		sql += " A.au_name as bk_acode ,"; // 이게 좋은 코드는 아닌데 기존 VO를 사용하기위한 꼼수
		sql += " bk_date,";
		sql += " bk_price,";
		sql += " bk_pages ";
		sql += " FROM tbl_books B";
		sql += " 	LEFT JOIN tbl_author A";
		sql += " 		ON B.bk_acode = A.au_code";
		sql += " 	LEFT JOIN tbl_company C";
		sql += " 		ON B.bk_ccode = C.cp_code";
		
		/*
		 * jdbcTemplate.query(sql, return type)
		 * sql문을 실행한 후 return type형태로 
		 * 데이터를 변환하여 return 해달라.
		 */
		
		
		List<BookVO> books = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<BookVO>(BookVO.class)	
				// 내가 받는 data가 VO형태이니 그걸 받아서 내가 적은 위치에 담아달라. 그리고 List에도 담아달라.
				// ( 이전엔 sql문 넣고 결과문 받고 rSet에 담아서...  VO에 담고.. List에 담았었음 )
		);
		log.debug("SELECT {}", books.toString());
		return books;
	}

	@Override
	public BookVO findById(String pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BookVO vo) {
		
		
		String sql = " INSERT INTO tbl_books( ";
		sql += " bk_isbn,";
		sql += " bk_title,";
		sql += " bk_ccode,";
		sql += " bk_acode,";
		sql += " bk_date,";
		sql += " bk_price,";
		sql += " bk_pages) ";
		sql += " VALUES( ?,?,?,?,?,?,? )";
		
		Object[] params = new Object[] {
				vo.getBk_isbn(),
				vo.getBk_title(),
				vo.getBk_ccode(),
				vo.getBk_acode(),
				vo.getBk_date(),
				vo.getBk_price(),
				vo.getBk_pages()
		};
		// insert, update, delete 모두 update() method 사용
		return jdbcTemplate.update(sql, params);
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

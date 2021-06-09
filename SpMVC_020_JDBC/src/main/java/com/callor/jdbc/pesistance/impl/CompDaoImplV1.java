package com.callor.jdbc.pesistance.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callor.jdbc.model.CompVO;
import com.callor.jdbc.pesistance.CompDao;

import lombok.extern.slf4j.Slf4j;
/*
 * @Component
 * 모든 Component를 대표하는 Annotation
 * 컴파일과정에서 다소 비용이 많이 소요된다.
 * 
 * Component Annotation
 * @Controller, @Service, @Repository 이러한 Annotation을 사용한다.
 * Spring Container에게 이 표식이 부착된 클래스를 
 * bean으로 생성하여 보관해달라는 지시어
 * (비용적인 측면때문에 전부 @Component로 쓰지않고
 * 	각각 쓸모에 맞는 Annotation을 사용한다.)
 * 
 * 
 * 
 * CompVO c = new CompVO() // 비용 적음
 * Object o = new CompVO() // 비용이 많이 소모됨
 * 
 * 
 * CompServiceImplV1 cs = new CompServiceImplV1() 
 * 이렇게 쓰는 게 가장 비용이 적게 소모됨
 * 
 * CompService cs1 = new CompServiceImplV1();
 * 다소 비용이 더 소모되긴 하지만 interface의 사용시 이점이 있어서
 * 인터페이스를 사용한다.
 * 
 */
@Slf4j
@Repository("compDaoV1") // bean으로 등록하기 위함
public class CompDaoImplV1 implements CompDao{

	protected final JdbcTemplate jdbcTemplate;
	public CompDaoImplV1(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<CompVO> selectAll() {
		// TODO Auto-generated method stub
		String sql = " SELECT * FROM tbl_company ";
		
		List<CompVO> compList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<CompVO>(CompVO.class)
				);
		log.debug("Comp Select {}", compList.toString());
		return null;
	}

	@Override
	public CompVO findById(String pk) {
		// TODO Auto-generated method stub
		String sql = "";
		Object[] params = new Object[] { pk };
		
		CompVO vo = (CompVO)jdbcTemplate.query(sql, 
				params,
				new BeanPropertyRowMapper<CompVO>(CompVO.class));
		
		return null;
	}

	@Override
	public int insert(CompVO vo) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO tbl_company ";
		sql += " (cp_code, cp_title, cp_ceo, cp_addr, cp_tel) ";
		sql += "VALUES( ?,?,?,?,? )";
		
		// 전달받은 VO를 오브젝트 배열로 만들어주고 
		Object[] params = new Object[] {
				vo.getCp_code(),
				vo.getCp_title(),
				vo.getCp_ceo(),
				vo.getCp_addr(),
				vo.getCp_tel()
		};
		
		// update method 에게 sql 명령문 문자열하고 Object 배열로 만들어준 params를 보내준다.
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int update(CompVO VO) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * jdbcTemplate를 사용하여 QUERY를 실행할 때
	 * 각 method에서 매개변수를 받아
	 * QUERY에 전달할텐데
	 * 이때 매개변수는 primitive로 받으면 값을 변환시키는 어려움이 있다.
	 * 때문에 매개변수는 wrapper class type으로 설정하는 것이 권장된다.
	 * 즉, 숫자형일 경우 int, long 대신 Integer, Long 형으로 선언하자.
	 * 
	 * vo에 담겨서 전달된 값은 Object[] 배열로 변환한 후
	 * jdbcTemplate에 전달해 주어야 한다.
	 * 
	 * 하지만, 1 ~ 2개 정도의 값을 전달할 때 Object[] 배여롤 변환 후
	 * 전달하면 Object 객체 저장소가 생성되고 메모리를 사용한다.
	 * 이때 전달되는 변수가 wrapper class type이면
	 * Object[] 로 만들지 않고 바로 주입할 수 있다.
	 */
	@Override
	public int delete(String cpcode) {
		// TODO 출판사 정보 삭제
		String sql = " DELETE FROM tbl_company ";
		sql += " WHERE cp_code = ? ";
		
		// cpcode가 String wrapper class type 이므로
		// Object[] 배열로 변환하지 않고 바로 전달이 가능하다.
//		Object[] params = new Object[] { cpcode };

		// cpcode가 class type인 String이라서 
		// 그대로 담아도 오류가 나지 않는다.
		// Object배열을 만들어서 메모리를 낭비할 필요가 없어짐
		return jdbcTemplate.update(sql, cpcode);
		// return도 붙여주기
		
	}

	@Override
	public List<CompVO> findByCName(String cname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompVO> findByTel(String tel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompVO> findByCeo(String ceo) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * tbl_company table에서 cpcode(출판사코드) 중
	 * 가장 큰 값을 추출하기
	 */
	@Override
	public String findByMaxCode() {
		String sql = " SELECT MAX(cp_code) FROM tbl_company ";
		
		// 원래 query만 썼는데 오류나서.. 기본형일때 그런다고? 뭔소리지 
		// queryForObject로 바꿈
		String cpCode = (String)jdbcTemplate.queryForObject(sql, String.class);
		
		return cpCode;
	}

}

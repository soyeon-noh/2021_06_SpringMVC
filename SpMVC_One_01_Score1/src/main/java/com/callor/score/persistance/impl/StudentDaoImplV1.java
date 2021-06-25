package com.callor.score.persistance.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.callor.score.model.ScoreVO;
import com.callor.score.model.StudentVO;
import com.callor.score.persistance.StudentDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("StudentDaoV1")
public class StudentDaoImplV1 implements StudentDao {

	protected final JdbcTemplate jdbcTemplate;
	public StudentDaoImplV1(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<StudentVO> selectAll() {
		String sql = " SELECT * FROM tbl_student ";

		List<StudentVO> stList 
			= jdbcTemplate.query(sql, 
					new BeanPropertyRowMapper<StudentVO>(StudentVO.class));
		log.debug("Student SelectAll {}", stList);
		return stList;
	}

	@Override
	public StudentVO findById(String st_num) {
		String sql = " SELECT * FROM tbl_student ";
		sql += " WHERE st_num = ? ";
		
		RowMapper<StudentVO> scoreMapper
			= new BeanPropertyRowMapper<StudentVO>(StudentVO.class);
		StudentVO student
			= (StudentVO) jdbcTemplate.query(sql, new Object[] {st_num}, scoreMapper);
		return student;
	}

	@Override
	public int insert(StudentVO vo) {
		String sql = " INSERT INTO tbl_student( ";
		sql += " st_num, ";
		sql += " st_name, ";
		sql += " st_dept, ";
		sql += " st_grade, ";
		sql += " st_tel, "; 
		sql += " st_addr ) ";
		sql += " VALUES( ?, ?, ?, ?, ?, ?) ";
		
		Object[] params = new Object[] {
				vo.getSt_num(),
				vo.getSt_name(),
				vo.getSt_dept(),
				vo.getSt_grade(),
				vo.getSt_tel(),
				vo.getSt_addr()
		};
		
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int update(StudentVO vo) {
		String sql = " UPDATE tbl_student SET ";
		sql += " st_name = ?, ";
		sql += " st_dept = ?, ";
		sql += " st_grade = ?, ";
		sql += " st_tel = ?, ";
		sql += " st_addr = ? ";
		sql += " WHERE st_num = ? ";
		

		Object[] params = new Object[] {
				vo.getSt_name(),
				vo.getSt_dept(),
				vo.getSt_grade(),
				vo.getSt_tel(),
				vo.getSt_addr(),
				vo.getSt_num()
		};
		
		
		return jdbcTemplate.update(sql, params); // 0을 넘어야 정상
	}

	@Override
	public int delete(String st_num) {
		String sql = " DELETE FROM tbl_student ";
		sql += " WHERE st_num = ? ";
		return jdbcTemplate.update(sql, st_num);
	}

}

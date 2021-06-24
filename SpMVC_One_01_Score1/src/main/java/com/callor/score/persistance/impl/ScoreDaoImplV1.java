package com.callor.score.persistance.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.callor.score.model.ScoreVO;
import com.callor.score.persistance.ScoreDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("ScoreDaoV1")
public class ScoreDaoImplV1 implements ScoreDao{

	protected final JdbcTemplate jdbcTemplate;
	public ScoreDaoImplV1(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<ScoreVO> selectAll() {
		String sql = " SELECT * FROM tbl_score ";
		
		RowMapper<ScoreVO> scoreMapper 
			= new BeanPropertyRowMapper<ScoreVO>(ScoreVO.class); // Mapper 부분 공부하
		List<ScoreVO> scList = jdbcTemplate.query(sql, scoreMapper);
		log.debug("Score SelectAll {}", scList);
		return scList;
	}

	@Override
	public ScoreVO findById(Long sc_seq) {
		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE sc_seq = ? ";
		
		RowMapper<ScoreVO> scoreMapper
			= new BeanPropertyRowMapper<ScoreVO>(ScoreVO.class);
		ScoreVO score 
			= (ScoreVO) jdbcTemplate.query(sql, new Object[] {sc_seq}, scoreMapper);
		return score;
	}
	
	public List<ScoreVO> findByNum(Long sc_stnum) {
		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE sc_stnum = ? ";
		
		RowMapper<ScoreVO> scoreMapper
			= new BeanPropertyRowMapper<ScoreVO>(ScoreVO.class);
		List<ScoreVO> score 
			= jdbcTemplate.query(sql, new Object[] {sc_stnum}, scoreMapper);
		return score;
	}

	@Override
	public int insert(ScoreVO vo) {
		String sql = " INSERT INTO tbl_score( ";
		sql += " sc_stnum, sc_subject, sc_score) ";
		sql += " VALUES( ?, ?, ? ) ";
		
		Object[] params = new Object[] {
				vo.getSc_stnum(),
				vo.getSc_subject(),
				vo.getSc_score()
		};
		
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int update(ScoreVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long sc_seq) {
		String sql = " DELETE FROM tbl_score ";
		sql += " WHERE sc_seq = ? ";
		return jdbcTemplate.update(sql, sc_seq);
	}

}

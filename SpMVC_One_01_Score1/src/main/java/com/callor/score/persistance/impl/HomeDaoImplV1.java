package com.callor.score.persistance.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callor.score.model.HomeDTO;
import com.callor.score.persistance.HomeDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("HomeDaoV1")
public class HomeDaoImplV1 implements HomeDao{

	protected final JdbcTemplate jdbcTemplate;
	public HomeDaoImplV1(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<HomeDTO> selectAll() {
		String sql = " SELECT * FROM view_HOME ";
		
		List<HomeDTO> homeList 
			= jdbcTemplate.query(sql, 
					new BeanPropertyRowMapper<HomeDTO>(HomeDTO.class));
		log.debug("Home SelectAll {}", homeList);
		return homeList;
	}

	@Override
	public HomeDTO findById(String pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(HomeDTO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(HomeDTO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

}

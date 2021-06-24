package com.callor.score.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.score.dao.ext.ScoreDao;
import com.callor.score.model.ScoreDTO;
import com.callor.score.service.ScoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("scoreServiceV1")
public class ScoreServiceImplV1 implements ScoreService{

	protected final ScoreDao scDao;
	
	public ScoreServiceImplV1(ScoreDao scDao) {
		this.scDao = scDao;
	}
	
	@Override
	public List<ScoreDTO> selectViewAll() {
		
		List<ScoreDTO> scList = scDao.selectViewAll();
		log.debug("Score selectViewAll {}", scList);
		
		return scList;
	}

}

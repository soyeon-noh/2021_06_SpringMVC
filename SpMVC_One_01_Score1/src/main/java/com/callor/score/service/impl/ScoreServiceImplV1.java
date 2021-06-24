package com.callor.score.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.score.model.ScoreVO;
import com.callor.score.persistance.ScoreDao;
import com.callor.score.service.ScoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("scoreServiceV1")
public class ScoreServiceImplV1 implements ScoreService{

	protected final ScoreDao scoreDao;
	public ScoreServiceImplV1(ScoreDao scoreDao) {
		 this.scoreDao = scoreDao;
	}
	
	@Override
	public List<ScoreVO> showList() {
		return scoreDao.selectAll();
	}

	@Override
	public List<ScoreVO> showfindByNum(String sc_stnum) {
		return scoreDao.findByNum(sc_stnum);
	}

}

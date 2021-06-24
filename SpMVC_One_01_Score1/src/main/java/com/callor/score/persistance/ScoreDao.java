package com.callor.score.persistance;

import java.util.List;

import com.callor.score.model.ScoreVO;

public interface ScoreDao extends GenericDao<ScoreVO, Long>{
	public List<ScoreVO> findByNum(String sc_stnum);
}

package com.callor.score.service;

import java.util.List;

import com.callor.score.model.ScoreVO;

public interface ScoreService {

	public List<ScoreVO> showList();
	public List<ScoreVO> showfindByNum(String sc_stnum);
	public int insertScore(ScoreVO vo);
	
}

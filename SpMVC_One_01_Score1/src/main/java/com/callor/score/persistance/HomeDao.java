package com.callor.score.persistance;

import java.util.List;

import com.callor.score.model.HomeDTO;

public interface HomeDao extends GenericDao<HomeDTO, String>{

	public List<HomeDTO> findByNum(String h_num);
}

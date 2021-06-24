package com.callor.score.service;

import java.util.List;

import com.callor.score.model.HomeDTO;

public interface HomeService {

	public List<HomeDTO> showList();
	public HomeDTO findById(String num);
}

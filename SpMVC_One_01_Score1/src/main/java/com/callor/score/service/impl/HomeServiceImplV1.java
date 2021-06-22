package com.callor.score.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.score.model.HomeDTO;
import com.callor.score.persistance.HomeDao;
import com.callor.score.service.HomeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("homeServiceV1")
public class HomeServiceImplV1 implements HomeService{

	protected final HomeDao homeDao;
	public HomeServiceImplV1(HomeDao homeDao) {
		this.homeDao = homeDao;
	}
	
	
	
	@Override
	public List<HomeDTO> homeList() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

}

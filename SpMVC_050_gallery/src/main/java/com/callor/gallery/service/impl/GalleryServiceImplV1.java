package com.callor.gallery.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.persistance.ext.GalleryDao;
import com.callor.gallery.service.FileService;
import com.callor.gallery.service.GalleryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service("galleryServiceV1")
public class GalleryServiceImplV1 implements GalleryService{
	
	protected final GalleryDao gaDao;
	
	@Qualifier("fileServiceV2") // 이거 안넣어주면 1,2가 다나옴(오류)
	protected final FileService fService;
	
	/*
	 * @Autowired가 설정된 변수, method, 객체 등을 만나면
	 * Spring framework는 변수를 초기화, 
	 * 		method를 실행하여 또 변수 초기화
	 * 		이미 생성되어 준비된 객체에 주입 등을 수행한다.
	 * 
	 * (생성자에 이런걸쓰면 오류가 많이 발생할 수 있어서 
	 * 	이렇게 Spring을 속여 자동으로 생성되는 코드를쓴다.)
	 * 
	 * 프로젝트가 Run함과 동시에 실행되며 Dao에 있는 create를 실행하게 된다.
	 */
	
	@Autowired
	public void create_table(GalleryDao gDao) { 
		// 여기 Parameters가 없으면 콘솔에 @Autowired는 parameters가 필요하다고 뜸..
		// 그래서 아무거나 안에 넣어주는 것이다.
		
		Map<String, String> maps = new HashMap<String, String>();
		gaDao.create_tabel(maps);
	}
	
	@Override
	public int insert(GalleryDTO galleryDTO) throws Exception{
		// TODO Auto-generated method stub
		return 0;
	}

}

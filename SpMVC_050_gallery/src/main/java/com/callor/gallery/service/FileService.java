package com.callor.gallery.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface FileService {
	
	// 1개의 파일을 Upload하고 Upload한 파일 이름을 return
	public String fileUp(MultipartFile file) throws Exception;
	// 다수의 파일을 Upload하고 Upload 후 파일들 이름을 return
	public List<String> filesUp(MultipartHttpServletRequest files) throws Exception;
	
	
	// 파일가져오는 경우엔 throws Exception이.. 거의 필수래
	// 그만큼 이슈가 많이 생기는듯
}

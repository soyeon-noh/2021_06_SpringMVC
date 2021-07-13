package com.callor.gallery.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("fileServiceV1")
public class FileServiceImplV1 implements FileService{

	// 서버의 특별한 폴더에 접근하기 위해 
	// 서버의 정보를 가져오기 위한 helper class
	@Autowired
	private ResourceLoader resLoader; // system관련 (파일)
	//하다보니 생성자 문제가생겨서 ... 여기선 autowired을 쓰고
	// V2에서 RequiredArgsConstructor사용
	
	@Override
	public String fileUp(MultipartFile file) throws Exception {
		// MultipartFile file 에 파일정보가 담겨있음
		
		// 파일정보에서 파일이름 추출하기
		String originalFileName = file.getOriginalFilename();
		// 파일 이름이 비어있으면 null을 return
		//(null과 좀 다르대)
		if(originalFileName.isEmpty()) {
			return null;
		}
		
		log.debug("파일이름 {}", originalFileName);
		
		// (oi의.. 뭐시기를 import)
		// file-context.xml에 설정된 
		// files 정보를 가져오기
		// 여기에 파일을 Upload 저장할 예정
		Resource res = resLoader.getResource("/files");
		log.debug("path : {}", res.getURI().getPath());
		//	C:/bizworks/Spring-MVC/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpMVC_050_gallery/files/
		// 여기! 서버에있는 이 경로에 저장하겠다는 뜻! 
		// package Explorer의 files가 아니라!

		// ~~~~/Project/files 경로를 가져오는것
		String filePath = res.getURI().getPath();
		
		// 같은이름의 image file로 다른 사람이 기존 이미지를 침범하는 것을 방지
		/*
		 * 파일을 업로드할때 보안 주의하기
		 * 실제 파일이름으로 업로드를 수행하면
		 * 쉽게 업로드를 구현할 수 있다.
		 * 
		 * 하지만
		 * 만약 같은 이름의 파일을 중복해서 업로드하면
		 * 먼저 업로드 된 파일이 변경되는 문제가 발생한다.
		 * 
		 * 해커에 의해서 같은 이름으로 파일들을 업로드 해 버리면
		 * 저장된 원래 파일들이 모두 변조될 수 있다.
		 * 
		 * 이러한 문제를 방지하기 위하여 
		 * UUID 문자열을 생성하고 
		 * UUID + 원래파일이름.원래확장자 형식으로 업로드를 수행한다.
		 * 이런방식으로 파일을 저장하면 해킹의 위험을 다소 막을 수 있다.
		 */
		String strUUID = UUID.randomUUID().toString();
		strUUID += originalFileName;
		
		// (java io의 file)
		// ( /files + "/" + originalFileName 이렇게 만들어야하는데
		// 아래 class 이용하면 편하다 ) 
		// 파일업로드할 path와 파일이름을 + 하여 
		// 업로드 준비
		File uploadPathAndFile = new File(filePath, strUUID);
		
		// file 에 담긴 파일정보를 사용하여 
		// uploadPathAndFile에 전송하라(복사하라, 업로드하라)
		file.transferTo(uploadPathAndFile); // 이거 하나면 충분하다!
		
		return strUUID;
	}

	@Override
	public List<String> filesUp(MultipartHttpServletRequest files) throws Exception {

		List<String> fileNames = new ArrayList<String>();
		String tagName = "m_file";
		
		List<MultipartFile> fileList = files.getFiles(tagName);
		for(MultipartFile file : fileList) {
			String fileName = this.fileUp(file);
			fileNames.add(fileName);
		}
		
		return fileNames;
	}
	
	@Override
	public int delete(String imgFileName) {
		return 0;
	}


}

package com.callor.gallery.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.model.FileDTO;
import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.model.GalleryFilesDTO;
import com.callor.gallery.persistance.ext.FileDao;
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
	protected final FileDao fDao;
	
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
		
		//int maps = 0;
		gaDao.create_tabel(maps);
		fDao.create_tabel(maps);
	}
	
	@Override
	public int insert(GalleryDTO galleryDTO) throws Exception{
		// TODO 모양만만들어놓고 사용하지않을거다
		return 0;
	}

	@Override
	public void insert(GalleryDTO gaDTO,
						MultipartFile one_file, 
						MultipartHttpServletRequest m_file) throws Exception {
		// TODO 우리가 필요한건 이 insert
		
		// 대표이미지가 업로드 되면...
		// 이미지를 서버에 저장하고 
		// 저장된 파일의 이름을 return 받기
		String strUUID = fService.fileUp(one_file);
		
		// DTO에 이미지 이름을 저장하기
		gaDTO.setG_image(strUUID);
		
		log.debug(" INSERT 전 seq {} ", gaDTO.getG_seq()); // 여기는 null
		
		// GalleryDTO에 담긴 데이터를 tbl_gallery table에 insert하기.
		// mapper에서  insert를 수행한 후 
		// 		새로생성된 g_seq값을 selectKey하여
		// 		gaDTO의 g_seq 변수에 담아놓은 상태이다.
		
		gaDao.insert(gaDTO);
		
		log.debug(" INSERT 후 seq {} ", gaDTO.getG_seq()); // 여기는 방금올린것의 seq
		
		// 갤러리 게시판 seq 값과 파일들을 묶어서 insert 하기 위한
		// 준비하기
		Long g_seq = gaDTO.getG_seq();
		
		List<FileDTO> files = new ArrayList<FileDTO>();
		
		// 업로드된 멀티파일을 서버에 업로드 하고
		// 원래 파일이름과 UUID 가 첨가된 파일이름을 추출하여
		// FileDTO에 담고
		// 다시 List에 담아 놓는다.
		
		List<MultipartFile> mFiles = m_file.getFiles("m_file");
		for(MultipartFile file : mFiles) {
			
			String fileOriginName = file.getOriginalFilename();
			String fileUUName = fService.fileUp(file);
			
			FileDTO fDTO = FileDTO.builder()
					.file_gseq(g_seq)
					.file_original(fileOriginName)
					.file_upname(fileUUName)
					.build();
			files.add(fDTO);
		}
		
		log.debug("이미지들 {}", files.toString());
		
		fDao.insertWithList(files);
	}

	@Override
	public List<GalleryDTO> selectAll() throws Exception {
		// TODO Auto-generated method stub
		
		List<GalleryDTO> gaList = gaDao.selectAll();
		log.debug("갤러리 리스트 {}", gaList.toString());
		return gaList;
	}

	@Override
	public List<GalleryFilesDTO> findByGalleryFiles(Long g_seq) {
		
		return gaDao.findByIdGalleryFiles(g_seq);
	}
}

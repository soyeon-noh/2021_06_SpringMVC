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

/*
 * final로 선언된 Inject 변수의 초기화를 시키는데 필요한 생성자를
 * 자동으로 만들어주는 lombok의 기능이다.
 * 
 * 클래스를 상속하면 @RequireArgsConstructor는 
 * 상속받은 클래스에서 사용 불가 
 * ( V1에서는 가능 V2에서는 못씀!)
 */
@RequiredArgsConstructor
@Slf4j
@Service("galleryServiceV1")
public class GalleryServiceImplV1 implements GalleryService{
	
	//뭔가오류가 생겨서 private로 바꿈
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
		
		List<GalleryFilesDTO> gfList = gaDao.findByIdGalleryFiles(g_seq);
		/*
		 * Dao로부터 select를 한 후에 데이터 검증을 하기 위해 사용하는 코드
		 * gfList 데이터가 조회되지 않으면 null이 발생할 수 있다.
		 * 		-> if(gfList != null) 으로 exception 방지
		 *  	-> gfList.size() > 0 를 추가하면 더 안전한 코드
		 */
		if(gfList != null && gfList.size() > 0) {
			log.debug(gfList.toString());
		} else {
			log.debug("조회된 데이터가 없음");
		}
		return gfList;
	}

	@Override
	public GalleryDTO findByIdGellery(Long g_seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Long g_seq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int file_delete(Long g_seq) {
		// TODO Auto-generated method stub
		
		// 파일을 삭제하기 위하여 저장된 파일 정보를 SELECT 하기
		FileDTO fDTO = fDao.findById(g_seq);
		
		// 업로드되어 저장된 파일을 삭제
		int ret = fService.delete(fDTO.getFile_upname());
		if( ret > 0 ) {
			// tbl_files table 에서 데이터를 삭제 (실제 데이터 삭제)
			ret = fDao.delete(g_seq);
		}
		return ret;
	}

	/*
	* pageNum을 매개변수로 받아서 selectAll 한 데이터를 잘라내고 pageNum에 해당하는 list 부분만 return하기
	* 
	* 한 페이지에 보여줄 list는 10개
	*/
	@Override
	public List<GalleryDTO> selectAllPage(int pageNum) throws Exception {
		
		// 1 전체데이터 SELECT 하기
		List<GalleryDTO> gaListAll = gaDao.selectAll();
		
		// 2 pageNum가 1이라면 list에서 0번째 요소 ~ 9번째 요소까지 추출하기
		//	 pageNum가 2라면 list에서 10번째 요소 ~ 19번째 요소까지 추출하기
		//	 pageNum가 3이라면 list에서 20번째 요소 ~ 29번째 요소까지 추출하기
		
		int totalCount = gaListAll.size();
		
		int start = (pageNum - 1) * 10; // pageNum가 n일때 그 page의 시작글 번호는 (n-1)*10 
		int end = pageNum * 10;
		
		if(pageNum * 10 > totalCount - 10) { 
			end = totalCount;
			start = end - 10;
		}
		
		
		List<GalleryDTO> pageList = new ArrayList<>(); //java 1.8이후부터 뒤쪽<>에 안붙여도 된다..
		for(int i = start; i < end; i++) {
			pageList.add(gaListAll.get(i));
		}
		return pageList;
	}

	@Override
	public List<GalleryDTO> findBySearchPage(int pageNu, String search) {
		

		
		return null;
	}

	@Override
	public List<GalleryDTO> findBySearchOrderPage(int pageNum, String search, String column) {
		// TODO Auto-generated method stub
		return null;
	}
}

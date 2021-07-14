package com.callor.gallery.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.model.GalleryFilesDTO;

public interface GalleryService {

	public int insert(GalleryDTO galleryDTO) throws Exception;
	public void insert(GalleryDTO gaDTO, 
			MultipartFile one_file, 
			MultipartHttpServletRequest m_file) throws Exception;

	public List<GalleryDTO> selectAll() throws Exception;
	/*
	 * 조건에 관계없이 전체 리스트를 pagination을 적용한 List로 만들기
	 * 		pageNum 값을 매개변수로 받아서
	 * 		일정한 분량으로 잘라 List를 return 하기
	 */
	public List<GalleryDTO> selectAllPage(int pageNum) throws Exception;
	
	/*
	 * 검색 조건과 pageNum값을 매개변수로 받아서
	 * 조건 검색을 수행한 후 일정분량으로 잘라서 List를 return 한다.
	 */
	public List<GalleryDTO> findBySearchPage(int pageNu, String search);
	
	/*
	 * 검색조건, 정렬조건, pageNum값을 매개변수로 받아서
	 * List return 하기
	 */
	public List<GalleryDTO> findBySearchOrderPage(int pageNum, String search, String column);
	
	public List<GalleryFilesDTO> findByGalleryFiles(Long g_seq);
	
	public GalleryDTO findByIdGellery(Long g_seq);
	public int delete(Long g_seq);
	public int file_delete(Long g_seq);
}

package com.callor.gallery.persistance.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.callor.gallery.model.FileDTO;
import com.callor.gallery.persistance.GenericDao;

public interface FileDao extends GenericDao<FileDTO, Long>{

	public int insertOrUpdate(FileDTO fileDTO);
	public int insertWithList(
			@Param("filesList")List<FileDTO> filesList);
	// insertWithList에 collection으로 되어있는 값 이름을 매개변수로 넣어줘야함
	// 확실하게 하기위해 @Param 사용
	public int insertOrUpdateWithList(
			@Param("filesList")List<FileDTO> filesList);
	
}

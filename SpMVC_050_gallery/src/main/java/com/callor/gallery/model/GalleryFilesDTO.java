package com.callor.gallery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GalleryFilesDTO {
	// view니까 builder는 필요없을 듯!
	
	/*
	 * tbl_gallery와 tbl_files table을 join하여 select 한 데이터는
	 * 실제로는 1:N 관계로 생성된 결과이다.
	 * 
	 * 하지만 실제로 보여지는 List는 마치 tbl_gallery가 여러개 있는 것처럼 보인다.
	 * 
	 * 예) gallery 1개 데이터에 file이 3개 첨부된 경우
	 * 		보여지는 List는 다음과 같다
	 * ================================
	 * 1번 gallery		1번 파일
	 * 1번 gallery		2번 파일
	 * 1번 gallery		3번 파일
	 * ================================
	 * 실제로 gallery는 1개지만 추출된 데이터는 전체가 list이므로 
	 * 전체 view를 담을 DTO를 선언하고 List<DTO> 형 변수에 데이터를 담았다.
	 * 
	 * 실제 데이터와 상관없이 g_seq 등등 gallery 정보를 담을 변수들은
	 * 1개만 있으면 될 것을 file 개수만큼 강제로 생성하는 결과가 된다.
	 */
	
	// 여기가 1일때 (
	private Long g_seq;			//	bigint
	private String g_writer;	//	varchar(125)
	private String g_date;		//	varchar(10)
	private String g_time;		//	varchar(10)
	private String g_subject;	//	varchar(50)
	private String g_content;	//	varchar(1000)
	private String g_image;		//	varchar(256)
	
	// 여기가 N
	private Long f_seq;			//	bigint
	private String f_original;	//	varchar(125)
	private String f_upname;	//	varchar(125)
	
	// 1:N 관계를 담다보니 N에 맞춰서 List가 만들어지는데...
	// 1인 속성들은 Java에서 쓸데없이 N만큼 생성됨...
}

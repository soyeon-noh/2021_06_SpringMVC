package com.callor.gallery.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.model.GalleryFilesDTO;
import com.callor.gallery.model.MemberVO;
import com.callor.gallery.service.GalleryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	
	@Qualifier("galleryServiceV2")
	protected final GalleryService gaService;

// ---------------------------------------------------------------------------------------------
	
	/*
	 * 주소창에 직접 입력후 Enter 로 요청할 때 Request를 처리
	 * 		http://localhost:8080/rootPath/gallery/dumy
	 * a tag 를 클릭했을 때
	 * 		<a href="${rootPath}/gallery/dumy>열기</a>
	 * JS
	 * 		location.href="${rootPath}/gallery/dumy" 가 실행됐을때
	 */
	
	@RequestMapping(value="/dumy", method=RequestMethod.GET)
	public String dumy() {
		return "home";
	}
	
	/*
	 * <form action = {${rootPath}/dumy" method="POST">
	 * 		<input name="str>
	 * 		<button type="submit">전송</button>
	 * </form>
	 */
	
	@RequestMapping(value="/dumy", method=RequestMethod.POST)
	public String dumy(String str) {
		return "home";
	}
	
	
// ---------------------------------------------------------------------------------------------	
	
	
	// localhost:8080/rootPath/gallery/ 또는
	// localhost:8080/rootPath/gallery 로 요청했을 때
	@RequestMapping(value= {"/",""}, method=RequestMethod.GET)
	public String list(
		@RequestParam(value ="pageNum", 
			required = false, 
			defaultValue = "1") String pageNum, 
		@RequestParam(value ="search_column",
			required = false,
			defaultValue = "NONE") String search_column,
		@RequestParam(value ="search_text",
			required = false,
			defaultValue = "NONE") String search_text,
		Model model) throws Exception {
		
		int intPageNum = Integer.valueOf(pageNum);
		if(intPageNum > 0) {
			model.addAttribute("PAGE_NUM",intPageNum);
		}
		
		// tbl_gallery table 전체 List를 가져와서
		// 전체 리스트를 표시하기 위해서 몇페이지의 nav가 필요한지를 찾자
		// List<GalleryDTO> gaList = gaService.selectAllPage(intPageNum, model);
		// model.addAttribute("GALLERYS", gaList);
		
		// search_column, search_text를 사용하여 조건검색
		gaService.findBySearchPage(
				search_column, search_text, intPageNum, model);
		
		model.addAttribute("BODY", "GA-LIST");
		return "home";
	}
	
	
	@RequestMapping(value="/input", method=RequestMethod.GET)
	public String input(Model model, HttpSession session) {
		
		// login 필요한 서비스마다 GET에 이거 넣어줘야한다. 
		// (나중엔 통합으로 할건데 일단 지금은!)
		MemberVO mVO = (MemberVO) session.getAttribute("MEMBER"); 
		if(mVO == null) {
			return "redirect:/member/login";
		}
		
		Date date = new Date(System.currentTimeMillis()); //java.util의 date
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("hh:mm:ss");
		
		String curDate = sd.format(date);
		String curTime = st.format(date);
		
		GalleryDTO gaDTO = GalleryDTO.builder()
							.g_date(curDate) // 갤러리 데이터 table의 PK값
							.g_time(curTime)
							.g_writer("soyeon")
							.build();
		
		model.addAttribute("CMD", gaDTO);
		model.addAttribute("BODY", "GA-INPUT");
		return "home";
	}
	
	@RequestMapping(value="/input", method=RequestMethod.POST)
	public String input(GalleryDTO gaDTO, 
						MultipartFile one_file,
						MultipartHttpServletRequest m_file,
						Model model) throws Exception {
		
		log.debug("갤러리 정보 {}", gaDTO.toString());
		log.debug("싱글 파일 {}", one_file.getOriginalFilename()); // 파일이름
		log.debug("멀티 파일 {}", m_file.getFileMap().toString());
		
		gaService.insert(gaDTO, one_file, m_file); // 통합해서 그대로 넘겨준다.
		
		return "redirect:/gallery";
	}
	
	@RequestMapping(value="/detail/{seq}", method=RequestMethod.GET)
	public String detail(@PathVariable("seq") String seq, Model model) {
		
		Long g_seq = 0L;
		

		// 임의의 시퀀스 값을 보내서.. 해킹을 막는 코드(확실x)
		try {
			g_seq = Long.valueOf(seq);
		} catch (NumberFormatException e) {
			// (블럭싸고 shift + alt + z로 try-catch)
			return "redirect:/gallery";
		}
		
		List<GalleryFilesDTO> gfList 
			= gaService.findByGalleryFiles(g_seq);
		model.addAttribute("GFLIST", gfList);
		model.addAttribute("BODY", "GA-DETAIL");
		
		return "home";
	}
	
	@RequestMapping(value="/detail2/{seq}", method=RequestMethod.GET)
	public String detail(
			@PathVariable("seq") String seq, Model model, HttpSession session) {
		
		Long g_seq = 0L;
		try {
			g_seq = Long.valueOf(seq);
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("갤러리 ID 값 오류");
			return "redirect:/";
		}
		
		GalleryDTO galleryDTO = gaService.findByIdGellery(g_seq);
		model.addAttribute("GALLERY", galleryDTO);
		
		model.addAttribute("BODY", "GA-DETAIL-V2");
		
		return "home";
	}
	
	/*
	 * 첨부파일이 있는 게시물의 삭제
	 * 
	 */
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public String delete(
			@RequestParam("g_seq") String seq, HttpSession session) {
		
		// 삭제를 요구하면
		// 1. 로그인이 되었나 확인
		MemberVO memVO = (MemberVO) session.getAttribute("MEMBER"); // Object Type이다보니 add casting 해줘야함
		if(memVO == null) {
			return "redirect:/member/login"; // 로그인이 안되어있으면 로그인으로 이동
		}
		
		Long g_seq = 0L;
		try {
			g_seq = Long.valueOf(seq);
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("갤러리 SEQ 오류");
			return "redirect:/gallery";
		}
		
		
		gaService.delete(g_seq);
		
		return "redirect:/gallery"; // 로그인이 되어있으면 list화면으로 이동
	}
	
	//fetch에 아무 설정이 없으니 GET이래.. POST도 할 수 있나?!
	@ResponseBody //응답할때 ..... 문자열보내라..
	@RequestMapping(value="/file/delete/{seq}", method=RequestMethod.GET)
	public String file_delete(
			@PathVariable("seq") String seq) {
		
		Long g_seq = 0L;
		try {
			g_seq = Long.valueOf(seq);
		} catch (Exception e) {
			// TODO: handle exception
			return "FAIL_SEQ";
		}
		
		int ret = gaService.file_delete(g_seq);
		
		if(ret > 0) return "OK";
		else return "FAIL";
		
		// body없이 그냥 return 보내면 404오류된다.
	}
}

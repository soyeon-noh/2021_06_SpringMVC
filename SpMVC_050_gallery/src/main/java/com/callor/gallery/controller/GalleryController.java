package com.callor.gallery.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.model.GalleryFilesDTO;
import com.callor.gallery.service.GalleryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	
	protected final GalleryService gaService;

	@RequestMapping(value= {"/",""}, method=RequestMethod.GET)
	public String list(Model model) throws Exception {
		
		List<GalleryDTO> gaList = gaService.selectAll();
		model.addAttribute("GALLERYS", gaList);
		model.addAttribute("BODY", "GA-LIST");
		return "home";
	}
	
	
	@RequestMapping(value="input", method=RequestMethod.GET)
	public String input(Model model) {

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
}

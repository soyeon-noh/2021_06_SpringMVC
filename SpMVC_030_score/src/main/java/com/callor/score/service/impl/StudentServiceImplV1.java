package com.callor.score.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.callor.score.dao.ext.ScoreDao;
import com.callor.score.dao.ext.StudentDao;
import com.callor.score.dao.ext.SubjectDao;
import com.callor.score.model.ScoreDTO;
import com.callor.score.model.ScoreVO;
import com.callor.score.model.StudentVO;
import com.callor.score.model.SubjectVO;
import com.callor.score.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("studentServiceV1")
public class StudentServiceImplV1 implements StudentService{

	protected final StudentDao stDao;
	protected final ScoreDao scDao;
	protected final SubjectDao sbDao;
	
	@Override
	public List<StudentVO> selectAll() {
		List<StudentVO> stList = stDao.selectAll();
		List<ScoreVO> scList = scDao.selectAll();
		List<SubjectVO> sbList = sbDao.selectAll();
		List<ScoreDTO> scViewList = scDao.selectViewAll();
		
		log.debug("Service Student {} ", stList.toString());
		log.debug("Service Score {} ", scList.toString());
		log.debug("Service Subject {} ", sbList.toString());
		log.debug("Service Score View {} ", scViewList.toString());
		
		return stList;
		
		//return stDao.selectAll(); 이렇게 한줄만써도됨
	}

	@Override
	public Map<String, Object> selectMaps() {
		
		List<StudentVO> stList = stDao.selectAll();
		List<ScoreVO> scList = scDao.selectAll();
		List<SubjectVO> sbList = sbDao.selectAll();
		List<ScoreDTO> scViewList = scDao.selectViewAll();
		
		Map<String, Object> maps = new HashMap<String, Object>();
		
		maps.put("학생", stList);
		maps.put("점수", scList);
		maps.put("학생", sbList);
		maps.put("View", scViewList);
		
		return maps;
	}

	// 현재 날짜에서 연도를 추출하여 학번 만들기
	@Override
	public String makeStNum() {
		
		// 현재날짜에서 연도 문자열 생성하기
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sd = new SimpleDateFormat("yyyy");
		String curYear = sd.format(date);
		
		String newStNum = this.makeStNum(curYear);
		log.debug("현재 연도 {}, 생성된 학번 {}", curYear, newStNum);
		
		return newStNum;
	}

	@Override
	public String makeStNum(String prefix) {
		// TODO Auto-generated method stub
		
		String stNum = stDao.getMaxStNum();
		/*
		 * prefix 만큼의 문자열을 건너뛰고 나머지 부분을 추출하기
		 * stNum = "20210010" 이고
		 * prefix = "2021" 이라면
		 * stSeq = stNum.substring(4) 이런 형식의 코드가 생성된다
		 * stSeq에는 0010의 문자열만 남게 된다.
		 */
		String stSeq = stNum.substring(prefix.length());
		log.debug("학번 seq : {} ", stSeq);

		Integer intSeq = Integer.valueOf(stSeq) + 1;
		String newStNum = String.format("%s%04d", prefix, intSeq); 
		log.debug("새로 생성된 학번 {} : ", newStNum);
		
		return newStNum;
	}

	@Override
	public int insert(StudentVO stVO) {
		/*
		 * insert를 수행하는 시점에서 학번을 만들고 싶으면
		 * 
		 * String newStNum = this.makeStNum();
		 * stVO.setSt_num(newStNum);
		 * 
		 * 여기서 makStNum을 호출해서 학번을 만든다음에
		 * VO setting하고 insert에 넘겨주면 된다.
		 */
		return stDao.insert(stVO);
	}

	@Override
	public int update(StudentVO stVO) {
		// TODO Auto-generated method stub
		return 0;
	}

}

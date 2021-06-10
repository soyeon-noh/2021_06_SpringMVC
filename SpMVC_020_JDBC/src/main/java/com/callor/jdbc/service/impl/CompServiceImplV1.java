package com.callor.jdbc.service.impl;

import org.springframework.stereotype.Service;

import com.callor.jdbc.model.CompVO;
import com.callor.jdbc.pesistance.CompDao;
import com.callor.jdbc.service.CompService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("compServiceV1")
public class CompServiceImplV1 implements CompService{
	
	protected final CompDao compDao;
	
	public CompServiceImplV1(CompDao compDao) {
		// 왼쪽 compDao는 final로 선언한거
		// 오른쪽 compDao는 매개변수로 가져온거
		this.compDao = compDao;
	}
	
	@Override
	public int insert(CompVO vo) {
		// TODO 동시에 insert에 접근해서 같은 번호가 만들어지는 것을 방지하기 위해
		// insert하기 직전에 ccode를 생성한다.
		// 트랜젝션을... 따로 설정해줄거래 
		String cpCode = compDao.findByMaxCode(); // 제일 큰 코드값을 가져오고
		log.debug("Cpcode {} ", cpCode);
		
		if(cpCode == null || cpCode.equals("")) { // DB에 아무것도 없다
			// C00001 를 만드는 format
			cpCode = String.format("C%04d", 1);
		} else { // DB에 뭔가가 있다
			
			// 영문 접두사 C를 자르고 숫자만 추출
			String _code = cpCode.substring(1); //원래 substring( 숫자, 숫자 ) 안쓰면 앞에서부터
			Integer intCode = Integer.valueOf(_code) + 1; // 숫자형으로바꾸고 +1
			
			cpCode = String.format("C%04d", intCode); // 다시 문자형으로 바꾸기
		}
		vo.setCp_code(cpCode);
		compDao.insert(vo);
		
		return 0;
	}

}
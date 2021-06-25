package com.callor.score.dao.ext;

import com.callor.score.dao.GenericDao;
import com.callor.score.model.StudentVO;

public interface StudentDao extends GenericDao<StudentVO, String>{
	
	// 학생테이블에 저장된 데이터에서
	// 가장 마지막의 학번(가장 큰 번호)를 추출하기
	public String getMaxStNum(); 
	//mapper에 등록하려면 Dao 메서드의 이름이 중요. 키값이기떄문! 복붙추천
}

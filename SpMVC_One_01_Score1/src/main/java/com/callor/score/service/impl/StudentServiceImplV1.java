package com.callor.score.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.score.model.StudentVO;
import com.callor.score.persistance.StudentDao;
import com.callor.score.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceImplV1 implements StudentService{

	protected final StudentDao studentDao;
	
	public StudentServiceImplV1(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	@Override
	public List<StudentVO> showList() {
		return studentDao.selectAll();
	}

}

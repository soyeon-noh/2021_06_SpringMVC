package com.callor.score.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor  //mybatis가 쓰는 코드

@ToString //우리가 쓰는 코드
public class SubjectAndScoreDTO {

	private String ss_code; 
	private String ss_stname; 
	private String ss_prof;
	private String ss_stnum; 
	private int ss_score;
}

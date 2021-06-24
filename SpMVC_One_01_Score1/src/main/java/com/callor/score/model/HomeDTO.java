package com.callor.score.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HomeDTO {

	private String h_num; //학번
	private String h_name; //이름
	private String h_dept; //전공
	private String h_grade; //학년
	private String h_count; // 과목수
	private String h_sum; //총합
	private String h_avg; //평균
}

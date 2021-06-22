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

	private String h_num;
	private String h_name;
	private String h_dept;
	private String h_grade;
	private String h_count;
	private String h_sum;
	private String h_avg;
}

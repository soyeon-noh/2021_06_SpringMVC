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

	private String t_num;
	private String t_name;
	private String t_dept;
	private String t_grade;
	private String t_count;
	private String t_sum;
	private String t_avg;
}

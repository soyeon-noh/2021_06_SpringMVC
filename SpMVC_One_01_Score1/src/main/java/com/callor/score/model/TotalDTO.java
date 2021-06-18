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
public class TotalDTO {

	private String 학번;
	private String 이름;
	private String 전공;
	private String 학년;
	private String 응시과목;
	private String 총점;
}

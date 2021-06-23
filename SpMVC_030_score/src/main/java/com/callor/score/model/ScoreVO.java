package com.callor.score.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScoreVO {

	private Long sc_seq;//	BIGINT
	private String sc_stnum;//	CHAR(8)
	private String sc_sbcode;//
	private int sc_score;//	INT

}

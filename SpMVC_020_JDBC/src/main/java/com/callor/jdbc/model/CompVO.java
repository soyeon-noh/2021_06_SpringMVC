package com.callor.jdbc.model;

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
public class CompVO {
	private String cp_code;
	private String cp_title;
	private String cp_ceo;
	private String cp_tel;
	private String cp_addr;
	private String cp_genre;

}

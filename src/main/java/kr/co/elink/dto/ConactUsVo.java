package kr.co.elink.dto;

import lombok.Data;

@Data
public class ConactUsVo extends DefaultVo
{
	private String conactId;
	
	private String conactNm;
	
	private String conactPhone;
	
	private String conactMail;
	
	private String conactContents;
	
	private String conactAgree;
	
	private String createdDatetime;
	
}

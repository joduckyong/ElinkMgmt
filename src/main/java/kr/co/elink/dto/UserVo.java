package kr.co.elink.dto;

import lombok.Data;

@Data
public class UserVo extends DefaultVo
{
	private String ci;
	
	private String telno;
	
	private String telcom;
	
	private String gender;
	
	private String brth;
	
	private String encryptKey;
}

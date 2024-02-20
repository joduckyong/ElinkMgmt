package kr.co.elink.dto;

import java.util.List;

import lombok.Data;

@Data
public class ContactUsVo extends DefaultVo
{
	private String id;
	
	private String contactId;
	
	private String contactType;
	
	private String contactNm;
	
	private String contactTitle;
	
	private String contactPhone;
	
	private String contactMail;
	
	private String contactContents;
	
	private String contactRecontents;
	
	private String contactPw;
	
	private String contactAgree;
	
	private String contactProcess;
	
	private String createdDatetime;
	
	private List<String> ids;
	
	private String encryptKey;
	
}

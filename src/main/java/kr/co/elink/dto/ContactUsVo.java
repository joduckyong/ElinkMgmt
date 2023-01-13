package kr.co.elink.dto;

import java.util.List;

import lombok.Data;

@Data
public class ContactUsVo extends DefaultVo
{
	private String id;
	
	private String contactId;
	
	private String contactNm;
	
	private String contactTitle;
	
	private String contactPhone;
	
	private String contactMail;
	
	private String contactContents;
	
	private String contactAgree;
	
	private String createdDatetime;
	
	private List<String> ids;
	
	private String encryptKey;
	
}

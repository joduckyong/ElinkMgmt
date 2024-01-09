package kr.co.elink.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ContactUsRVo
{
	
	private String contactId;
	
	private String contactNm;
	
	private String contactTitle;
	
	private String contactPhone;
	
	private String contactMail;
	
	private String contactContents;
	
	private String contactAgree;
	
	private String contactProcess;
	
	private String createdDatetime;
	
	/** row number */
    private int rnum = 0;
	
	@JsonIgnore
	private int totalCount;
	
}

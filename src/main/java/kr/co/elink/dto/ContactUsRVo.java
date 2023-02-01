package kr.co.elink.dto;

import java.util.List;

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
	
	private String createdDatetime;
	
	/** row number */
    private int rnum = 0;
    
    /** row number */
    private int num = 0;
	
	@JsonIgnore
	private int totalCount;
	
}

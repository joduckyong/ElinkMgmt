package kr.co.elink.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class OutlineRVo
{
	/** 연혁 번호 */
	private int companyId;
	
	/** 년 */
    private String companyYear;
    
    /** 월 */
    private String companyMonth;
    
    /** 일 */
    private String companyDay;
    
    /** 내용 */
    private String companyContents;
    
    private String createdId;
	
	private String createdDatetime;
	
	private String updatedId;
	
	private String updatedDatetime;
	
	@JsonIgnore
	private int totalCount;
    
}

package kr.co.elink.dto;

import java.util.List;

import lombok.Data;

@Data
public class OutlineVo extends DefaultVo
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
	
	private List<String> ids;
    
}

package kr.co.elink.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class PopupRVo 
{
	
	private String popupId;
	
	private String popupTitle;
	
	private String popupLink;
	
	private String popupClose1;
	
	private String popupClose2;
	
	private String popupHeight;
	
	private String popupWidth;
	
	private String popupStartdate;
	
	private String popupEnddate;
	
	private String createdId;
	
	private String createdDatetime;
	
	private String updatedId;
	
	private String updatedDatetime;
	
	private String thumbNm;
	
	private String thumbOriginNm;
	
	private long thumbSize;
	
	/** row number */
    private int rnum = 0;
    
    /** row number */
    private int num = 0;
	
	@JsonIgnore
	private int totalCount;
	
}

package kr.co.elink.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class BoardRVo
{
	
	private String boardId;
	
	private String boardType;
	
	private String boardTitle;
	
	private String boardContents;
	
	private String url;
	
	private String createdId;
	
	private String createdDatetime;
	
	private String createdDatetime2;
	
	private String updatedId;
	
	private String updatedDatetime;
	
	private String boardStartDatetime;
	
	private String boardEndDatetime;
	
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

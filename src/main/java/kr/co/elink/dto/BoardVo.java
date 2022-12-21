package kr.co.elink.dto;

import lombok.Data;

@Data
public class BoardVo
{
	private String boardId;
	
	private String boardType;
	
	private String boardTitle;
	
	private String boardContents;
	
	private String url;
	
	private String createdId;
	
	private String createdDatetime;
	
	private String updatedId;
	
	private String updatedDatetime;
}

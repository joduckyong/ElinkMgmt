package kr.co.elink.dto;

import java.util.List;

import lombok.Data;

@Data
public class BoardVo extends DefaultVo
{
	private String id;
	
	private String boardId;
	
	private String boardType;
	
	private String boardTitle;
	
	private String boardContents;
	
	private String url;
	
	private String createdId;
	
	private String createdDatetime;
	
	private String updatedId;
	
	private String updatedDatetime;
	
	private String boardStartDatetime;
	
	private String boardEndDatetime;
	
	private List<String> ids;
}

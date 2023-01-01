package kr.co.elink.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class FileVo {
	
	private String fileId;
	
	private String fileType;
	
	private String filePath;
	
	private String fileNm;
	
	private String fileOriginNm;
	
	private long fileSize;
	
	@JsonIgnore
	private List<String> ids;
	
}

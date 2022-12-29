package kr.co.elink.dto;

import lombok.Data;

@Data
public class FileVo {
	
	private String fileId;
	
	private String fileType;
	
	private String filePath;
	
	private String fileNm;
	
	private String fileOriginNm;
	
	private long fileSize;
	
}

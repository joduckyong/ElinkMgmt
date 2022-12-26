package kr.co.elink.dto;

import lombok.Data;

@Data
public class PopupVo extends DefaultVo
{
	private String id;
	
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
}

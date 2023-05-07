package kr.co.elink.eng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.elink.dto.PopupRVo;
import kr.co.elink.dto.PopupVo;

@Repository
@Mapper
public interface PopupEngMapper {
	
	public List<PopupRVo> selectEngPopup(PopupVo popupVo);
	
	public PopupRVo selectEngPopupInfo(String id);
	
	public int insertEngPopup(PopupVo popupVo);
	
	public int updateEngPopup(PopupVo popupVo);
	
	public int deleteEngPopup(String id);
	
	public int deleteEngPopupIds(PopupVo popupVo);
}

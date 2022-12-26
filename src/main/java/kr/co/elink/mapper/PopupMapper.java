package kr.co.elink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.elink.dto.PopupVo;

@Repository
@Mapper
public interface PopupMapper {
	
	public List<PopupVo> selectPopup(PopupVo popupVo);
	
	public PopupVo selectPopupInfo(String id);
	
	public int insertPopup(PopupVo popupVo);
	
	public int updatePopup(PopupVo popupVo);
	
	public int deletePopup(String id);
	
	public int deletePopupIds(List<String> ids);
}

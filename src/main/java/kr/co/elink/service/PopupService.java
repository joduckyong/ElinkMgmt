package kr.co.elink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.elink.dto.PopupVo;
import kr.co.elink.mapper.PopupMapper;

@Service
public class PopupService {
	
	@Autowired
	PopupMapper popupMapper;
	
	public List<PopupVo> selectPopup(String id, int pageIndex){
		
		// 페이징 처리
		PopupVo popupVo = new PopupVo();
		popupVo.setId(id);
		popupVo.setFirstIndex((pageIndex - 1) * popupVo.getRecordCountPerPage());
		popupVo.setLastIndex(popupVo.getRecordCountPerPage());		
		return popupMapper.selectPopup(popupVo);
	};
	
	public PopupVo selectPopupInfo(String id){
		return popupMapper.selectPopupInfo(id);
	};
	
	@Transactional
	public int insertPopup(PopupVo popupVo){
		return popupMapper.insertPopup(popupVo);
	};
	
	@Transactional
	public int updatePopup(PopupVo popupVo){
		return popupMapper.updatePopup(popupVo);
	};
	
	@Transactional
	public int deletePopup(String id){
		return popupMapper.deletePopup(id);
	};
	
	@Transactional
	public int deletePopupIds(PopupVo popupVo){
		return popupMapper.deletePopupIds(popupVo);
	}
}

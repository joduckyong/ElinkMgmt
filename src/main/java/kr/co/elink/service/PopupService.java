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
	
	public List<PopupVo> selectPopup(PopupVo popupVo){
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
	public int updatePopup(String id){
		return popupMapper.updatePopup(id);
	};
	
}

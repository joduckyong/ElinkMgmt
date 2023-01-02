package kr.co.elink.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.elink.common.util.UploadFile;
import kr.co.elink.common.util.UploadThumbnail;
import kr.co.elink.dto.FileVo;
import kr.co.elink.dto.PopupRVo;
import kr.co.elink.dto.PopupVo;
import kr.co.elink.mapper.FileMapper;
import kr.co.elink.mapper.PopupMapper;

@Service
public class PopupService {
	
	@Autowired
	PopupMapper popupMapper;
	
	@Autowired
	FileMapper fileMapper;
	
	@Autowired
	UploadFile uploadFile;
	
	@Autowired
	UploadThumbnail uploadThumbnail;
	
	public List<PopupRVo> selectPopup(String id, int pageIndex){
		
		// 페이징 처리
		PopupVo popupVo = new PopupVo();
		popupVo.setId(id);
		popupVo.setFirstIndex((pageIndex - 1) * popupVo.getRecordCountPerPage());
		popupVo.setLastIndex(popupVo.getRecordCountPerPage());		
		return popupMapper.selectPopup(popupVo);
	};
	
	public PopupRVo selectPopupInfo(String id){
		return popupMapper.selectPopupInfo(id);
	};
	
	@Transactional
	public int insertPopup(PopupVo popupVo, MultipartFile multipartThumbnail) throws IOException{
		int result = popupMapper.insertPopup(popupVo);
		
		if(multipartThumbnail != null) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFileId(popupVo.getId());
    		fileMapper.insertFile(uploadThumbnail.upload(multipartThumbnail, fileVo));
    	}
		
		return result;
	};
	
	@Transactional
	public int updatePopup(PopupVo popupVo, MultipartFile multipartThumbnail) throws IOException{
		
		if(multipartThumbnail != null) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFileId(popupVo.getPopupId());
    		fileVo.setIds(popupVo.getIds());
    		fileMapper.deleteFileForName(fileVo);
    		fileMapper.insertFile(uploadThumbnail.upload(multipartThumbnail, fileVo));
		}
		
		return popupMapper.updatePopup(popupVo);
	};
	
	@Transactional
	public int deletePopup(String id){
		return popupMapper.deletePopup(id);
	};
	
	@Transactional
	public int deletePopupIds(PopupVo popupVo){
		FileVo fileVo = new FileVo();
		fileVo.setIds(popupVo.getIds());
		fileMapper.deleteFileIds(fileVo);
		return popupMapper.deletePopupIds(popupVo);
	}
}

package kr.co.elink.eng.service;

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
import kr.co.elink.eng.mapper.PopupEngMapper;
import kr.co.elink.mapper.FileMapper;

@Service
public class PopupEngService {
	
	@Autowired
	PopupEngMapper popupEngMapper;
	
	@Autowired
	FileMapper fileMapper;
	
	@Autowired
	UploadFile uploadFile;
	
	@Autowired
	UploadThumbnail uploadThumbnail;
	
	public List<PopupRVo> selectEngPopup(String id, int pageIndex, String searchCondition){
		
		// 페이징 처리
		PopupVo popupVo = new PopupVo();
		popupVo.setId(id);
		if(searchCondition != null && !"".equals(searchCondition)) {
			popupVo.setSearchCondition(searchCondition);
		}
		popupVo.setFirstIndex((pageIndex - 1) * popupVo.getRecordCountPerPage());
		popupVo.setLastIndex(popupVo.getRecordCountPerPage());		
		return popupEngMapper.selectEngPopup(popupVo);
	};
	
	public PopupRVo selectEngPopupInfo(String id){
		return popupEngMapper.selectEngPopupInfo(id);
	};
	
	@Transactional
	public int insertEngPopup(PopupVo popupVo, MultipartFile multipartThumbnail, MultipartFile multipartFile) throws IOException{
		int result = popupEngMapper.insertEngPopup(popupVo);
		
		if(multipartThumbnail != null) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFileId(popupVo.getId());
    		fileMapper.insertFile(uploadThumbnail.upload(multipartThumbnail, fileVo));
    	}
		
    	if(multipartFile != null) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFileId(popupVo.getId());
    		fileMapper.insertFile(uploadFile.upload(multipartFile, fileVo));
    	}
		
		return result;
	};
	
	@Transactional
	public int updateEngPopup(PopupVo popupVo, MultipartFile multipartThumbnail, MultipartFile multipartFile) throws IOException{
		
		if(multipartThumbnail != null) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFileId(popupVo.getPopupId());
    		fileVo.setIds(popupVo.getIds());
    		fileMapper.deleteFileForName(fileVo);
    		fileMapper.insertFile(uploadThumbnail.upload(multipartThumbnail, fileVo));
		}
		
		if(multipartFile != null) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFileId(popupVo.getPopupId());
    		fileVo.setIds(popupVo.getIds());
    		fileMapper.deleteFileForName(fileVo);
    		fileMapper.insertFile(uploadFile.upload(multipartFile, fileVo));
		}
		
		return popupEngMapper.updateEngPopup(popupVo);
	};
	
	@Transactional
	public int deleteEngPopup(String id){
		return popupEngMapper.deleteEngPopup(id);
	};
	
	@Transactional
	public int deleteEngPopupIds(PopupVo popupVo){
		FileVo fileVo = new FileVo();
		fileVo.setIds(popupVo.getIds());
		fileMapper.deleteFileIds(fileVo);
		return popupEngMapper.deleteEngPopupIds(popupVo);
	}
}

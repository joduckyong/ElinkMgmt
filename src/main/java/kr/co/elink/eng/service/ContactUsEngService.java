package kr.co.elink.eng.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.elink.common.util.UploadFile;
import kr.co.elink.dto.ContactUsRVo;
import kr.co.elink.dto.ContactUsVo;
import kr.co.elink.dto.FileVo;
import kr.co.elink.eng.mapper.ContactUsEngMapper;
import kr.co.elink.mapper.FileMapper;

@Service
public class ContactUsEngService {
	
	@Autowired
	ContactUsEngMapper contactUsEngMapper;
	
	@Autowired
	FileMapper fileMapper;
	
	@Autowired
	UploadFile uploadFile;
	
	@Value("${encrypt.key}")
	private String encryptKey;
	
	public List<ContactUsRVo> selectEngContactUs(String id, String contactType, int pageIndex, String searchKeyword, String searchCondition){
		
		// 페이징 처리
		ContactUsVo contactUsVo = new ContactUsVo();
		contactUsVo.setId(id);
		contactUsVo.setContactType(contactType);
		contactUsVo.setEncryptKey(encryptKey);
		
		if(searchKeyword != null && !"".equals(searchKeyword)) {
			contactUsVo.setSearchKeyword(searchKeyword);
			contactUsVo.setSearchCondition(searchCondition);
		}
		contactUsVo.setFirstIndex((pageIndex - 1) * contactUsVo.getRecordCountPerPage());
		contactUsVo.setLastIndex(contactUsVo.getRecordCountPerPage());		
		return contactUsEngMapper.selectEngContactUs(contactUsVo);
		
	};
	
	public ContactUsRVo selectEngContactUsInfo(String id){
		ContactUsVo contactUsVo = new ContactUsVo();
		contactUsVo.setId(id);
		contactUsVo.setEncryptKey(encryptKey);
		
		return contactUsEngMapper.selectEngContactUsInfo(contactUsVo);
	};
	
	@Transactional
	public int insertEngContactUs(ContactUsVo contactUsVo, MultipartFile multipartFile) throws IOException{
		
		int result = -1;
		String acceptFileTypes = "^([\\S]+(\\.(?i)(jpg|gif|png|jpeg|pdf|hwp|xlsx|docx|ppt|pptx))$)";
		long acceptFileSize = 50 * 1024 * 1024;
		contactUsVo.setEncryptKey(encryptKey);
		
		result = contactUsEngMapper.insertEngContactUs(contactUsVo);
		
		if(multipartFile != null) {
			if(multipartFile.getOriginalFilename().matches(acceptFileTypes) && multipartFile.getSize() <= acceptFileSize) {
	    		FileVo fileVo = new FileVo();
	    		fileVo.setFileId(contactUsVo.getId());
	    		fileMapper.insertFile(uploadFile.upload(multipartFile, fileVo));
			}
		}
		
		return result;
	};
	
	@Transactional
	public int updateEngContactUs(ContactUsVo contactUsVo){
		return contactUsEngMapper.updateEngContactUs(contactUsVo);
	};
	
	@Transactional
	public int deleteEngContactUs(String id){
		return contactUsEngMapper.deleteEngContactUs(id);
	};
	
	@Transactional
	public int updateEngContactUsIds(ContactUsVo contactUsVo){
		return contactUsEngMapper.updateEngContactUsIds(contactUsVo);
	}
	
	@Transactional
	public int deleteEngContactUsIds(ContactUsVo contactUsVo){
		FileVo fileVo = new FileVo();
		fileVo.setIds(contactUsVo.getIds());
		fileMapper.deleteFileIds(fileVo);
		return contactUsEngMapper.deleteEngContactUsIds(contactUsVo);
	}
}

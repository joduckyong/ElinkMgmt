package kr.co.elink.eng.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.elink.common.util.UploadFile;
import kr.co.elink.common.util.UploadThumbnail;
import kr.co.elink.dto.BoardRVo;
import kr.co.elink.dto.BoardVo;
import kr.co.elink.dto.FileVo;
import kr.co.elink.dto.PrevNextVo;
import kr.co.elink.eng.mapper.BoardEngMapper;
import kr.co.elink.mapper.FileMapper;

@Service
public class BoardEngService {
	
	@Autowired
	BoardEngMapper boardEngMapper;
	
	@Autowired
	FileMapper fileMapper;
	
	@Autowired
	UploadFile uploadFile;
	
	@Autowired
	UploadThumbnail uploadThumbnail;
	
	public List<BoardRVo> selectEngBoard(String id, int pageIndex, String searchKeyword, String searchCondition){
		// 페이징 처리
		BoardVo boardVo = new BoardVo();
		boardVo.setId(id);
		if(searchKeyword != null && !"".equals(searchKeyword)) {
			boardVo.setSearchKeyword(searchKeyword);
			boardVo.setSearchCondition(searchCondition);
		}
		boardVo.setFirstIndex((pageIndex - 1) * boardVo.getRecordCountPerPage());
		boardVo.setLastIndex(boardVo.getRecordCountPerPage());
		
		return boardEngMapper.selectEngBoard(boardVo);
	};
	
	public List<BoardRVo> selectEngClientBoard(String id, int pageIndex, String searchKeyword, String searchCondition, String boardType){
		// 페이징 처리
		BoardVo boardVo = new BoardVo();
		boardVo.setId(id);
		if(searchKeyword != null && !"".equals(searchKeyword)) {
			boardVo.setSearchKeyword(searchKeyword);
			boardVo.setSearchCondition(searchCondition);
		}
		if(boardType != null && !"".equals(boardType)) {
			boardVo.setBoardType(boardType);
		}
		if(id.equals("MED")) {
			boardVo.setRecordCountPerPage(3);
		}
		boardVo.setFirstIndex((pageIndex - 1) * boardVo.getRecordCountPerPage());
		boardVo.setLastIndex(boardVo.getRecordCountPerPage());
		
		return boardEngMapper.selectEngBoard(boardVo);
	};
	
	public BoardRVo selectEngBoardInfo(String id){
		return boardEngMapper.selectEngBoardInfo(id);
	};
	
	@Transactional
	public int insertEngBoard(BoardVo boardVo){
		
		return boardEngMapper.insertEngBoard(boardVo);
	};
	
	@Transactional
	public int insertEngBoard(BoardVo boardVo, MultipartFile[] multipartFile) throws IOException{
		
		return boardEngMapper.insertEngBoard(boardVo);
	};
	
	@Transactional
	public int insertEngBoard(BoardVo boardVo, MultipartFile multipartThumbnail, MultipartFile multipartFile, List<MultipartFile> multipartFiles) throws IOException{
		
		int result = boardEngMapper.insertEngBoard(boardVo);
		
		if(multipartThumbnail != null) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFileId(boardVo.getId());
    		fileMapper.insertFile(uploadThumbnail.upload(multipartThumbnail, fileVo));
    	}
    	
    	if(multipartFile != null) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFileId(boardVo.getId());
    		fileMapper.insertFile(uploadFile.upload(multipartFile, fileVo));
    	}
    	
    	if(multipartFiles != null) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFileId(boardVo.getId());
    		for(MultipartFile file : multipartFiles) {
    			fileMapper.insertFile(uploadFile.upload(file, fileVo));
    		}
    	}
		
		return result;
	};
	
	@Transactional
	public int updateEngBoard(BoardVo boardVo, MultipartFile multipartThumbnail, MultipartFile multipartFile, List<MultipartFile> multipartFiles) throws IOException{
		
		
		if(boardVo.getIds() != null && boardVo.getIds().size() > 0) {
			FileVo fileVo = new FileVo();
			fileVo.setIds(boardVo.getIds());
    		fileMapper.deleteFileForName(fileVo);
		}
		
		if(multipartThumbnail != null) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFileId(boardVo.getBoardId());
    		fileMapper.insertFile(uploadThumbnail.upload(multipartThumbnail, fileVo));
		}
		
		if(multipartFile != null) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFileId(boardVo.getBoardId());
    		fileMapper.insertFile(uploadFile.upload(multipartFile, fileVo));
		}
		
		if(multipartFiles != null) {
			FileVo fileVo = new FileVo();
    		fileVo.setFileId(boardVo.getBoardId());
    		for(MultipartFile file : multipartFiles) {
    			fileMapper.insertFile(uploadFile.upload(file, fileVo));
    		}
    	}
		
		return boardEngMapper.updateEngBoard(boardVo);
	};
	
	@Transactional
	public int deleteEngBoard(String id){
		return boardEngMapper.deleteEngBoard(id);
	};
	
	@Transactional
	public int deleteEngBoardIds(BoardVo boardVo){
		FileVo fileVo = new FileVo();
		fileVo.setIds(boardVo.getIds());
		fileMapper.deleteFileIds(fileVo);
		return boardEngMapper.deleteEngBoardIds(boardVo);
	}
	
	public PrevNextVo selectEngPrevNextBoard(String id){
		return boardEngMapper.selectEngPrevNextBoard(id);
	};
	
	public String selectEngPinup(){
		return boardEngMapper.selectEngPinup();
	};
	
	@Transactional
	public int updateEngPinup(BoardVo boardVo){
		
		int result = 0;
		String pinupId = boardEngMapper.selectEngPinup();
		
		if(pinupId != null && !"".equals(pinupId)) {
			result = boardEngMapper.updateEngPinup(boardVo);
		}else {
			result = boardEngMapper.insertEngPinup(boardVo);
		}
		
		return result;
	};
	
}

package kr.co.elink.service;

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
import kr.co.elink.mapper.BoardMapper;
import kr.co.elink.mapper.FileMapper;

@Service
public class BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	
	@Autowired
	FileMapper fileMapper;
	
	@Autowired
	UploadFile uploadFile;
	
	@Autowired
	UploadThumbnail uploadThumbnail;
	
	public List<BoardRVo> selectBoard(String id, int pageIndex, String searchKeyword, String searchCondition){
		// 페이징 처리
		BoardVo boardVo = new BoardVo();
		boardVo.setId(id);
		if(searchKeyword != null && !"".equals(searchKeyword)) {
			boardVo.setSearchKeyword(searchKeyword);
			boardVo.setSearchCondition(searchCondition);
		}
		boardVo.setFirstIndex((pageIndex - 1) * boardVo.getRecordCountPerPage());
		boardVo.setLastIndex(boardVo.getRecordCountPerPage());
		
		return boardMapper.selectBoard(boardVo);
	};
	
	public List<BoardRVo> selectClientBoard(String id, int pageIndex, String searchKeyword, String searchCondition, String boardType){
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
		boardVo.setFirstIndex((pageIndex - 1) * boardVo.getRecordCountPerPage());
		boardVo.setLastIndex(boardVo.getRecordCountPerPage());
		
		return boardMapper.selectBoard(boardVo);
	};
	
	public BoardRVo selectBoardInfo(String id){
		return boardMapper.selectBoardInfo(id);
	};
	
	@Transactional
	public int insertBoard(BoardVo boardVo){
		
		return boardMapper.insertBoard(boardVo);
	};
	
	@Transactional
	public int insertBoard(BoardVo boardVo, MultipartFile[] multipartFile) throws IOException{
		
		return boardMapper.insertBoard(boardVo);
	};
	
	@Transactional
	public int insertBoard(BoardVo boardVo, MultipartFile multipartThumbnail, MultipartFile multipartFile, List<MultipartFile> multipartFiles) throws IOException{
		
		int result = boardMapper.insertBoard(boardVo);
		
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
	public int updateBoard(BoardVo boardVo, MultipartFile multipartThumbnail, MultipartFile multipartFile, List<MultipartFile> multipartFiles) throws IOException{
		
		if(multipartThumbnail != null) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFileId(boardVo.getBoardId());
    		fileVo.setIds(boardVo.getIds());
    		fileMapper.deleteFileForName(fileVo);
    		fileMapper.insertFile(uploadThumbnail.upload(multipartThumbnail, fileVo));
		}
		
		if(multipartFile != null) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFileId(boardVo.getBoardId());
    		fileVo.setIds(boardVo.getIds());
    		fileMapper.deleteFileForName(fileVo);
    		fileMapper.insertFile(uploadFile.upload(multipartFile, fileVo));
		}
		
		if(multipartFiles != null) {
			FileVo fileVo = new FileVo();
    		fileVo.setFileId(boardVo.getBoardId());
    		fileVo.setIds(boardVo.getIds());
    		fileMapper.deleteFileForName(fileVo);
    		for(MultipartFile file : multipartFiles) {
    			fileMapper.insertFile(uploadFile.upload(file, fileVo));
    		}
    	}
		
		return boardMapper.updateBoard(boardVo);
	};
	
	@Transactional
	public int deleteBoard(String id){
		return boardMapper.deleteBoard(id);
	};
	
	@Transactional
	public int deleteBoardIds(BoardVo boardVo){
		FileVo fileVo = new FileVo();
		fileVo.setIds(boardVo.getIds());
		fileMapper.deleteFileIds(fileVo);
		return boardMapper.deleteBoardIds(boardVo);
	}
	
	public PrevNextVo selectPrevNextBoard(String id){
		return boardMapper.selectPrevNextBoard(id);
	};
	
}

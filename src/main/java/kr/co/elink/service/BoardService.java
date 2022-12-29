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
	public int insertBoard(BoardVo boardVo, MultipartFile multipartThumbnail, MultipartFile multipartFile) throws IOException{
		
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
		
		return boardMapper.insertBoard(boardVo);
	};
	
	@Transactional
	public int updateBoard(BoardVo boardVo){
		return boardMapper.updateBoard(boardVo);
	};
	
	@Transactional
	public int deleteBoard(String id){
		return boardMapper.deleteBoard(id);
	};
	
	@Transactional
	public int deleteBoardIds(BoardVo boardVo){
		return boardMapper.deleteBoardIds(boardVo);
	}
}

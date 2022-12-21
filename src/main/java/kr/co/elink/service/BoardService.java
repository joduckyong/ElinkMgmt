package kr.co.elink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.elink.dto.BoardVo;
import kr.co.elink.mapper.BoardMapper;

@Service
public class BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	
	public List<BoardVo> selectBoard(BoardVo boardVo){
		return boardMapper.selectBoard(boardVo);
	};
	
	public BoardVo selectBoardInfo(String id){
		return boardMapper.selectBoardInfo(id);
	};
	
	@Transactional
	public List<BoardVo> insertBoard(BoardVo boardVo){
		
		boardMapper.insertBoard(boardVo);
		return boardMapper.selectBoard(boardVo);
	};
	
	@Transactional
	public BoardVo updateBoard(BoardVo boardVo){
		boardMapper.updateBoard(boardVo);
		String id = boardVo.getBoardId();
		return boardMapper.selectBoardInfo(id);
	};
	
	@Transactional
	public int deleteBoard(String id){
		return boardMapper.deleteBoard(id);
	};
	
}

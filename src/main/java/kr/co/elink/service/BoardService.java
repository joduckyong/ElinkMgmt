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
	
	public List<BoardVo> selectBoard(String id){
		return boardMapper.selectBoard(id);
	};
	
	public BoardVo selectBoardInfo(String id){
		return boardMapper.selectBoardInfo(id);
	};
	
	@Transactional
	public int insertBoard(BoardVo boardVo){
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
	
}

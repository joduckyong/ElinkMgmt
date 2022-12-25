package kr.co.elink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.elink.dto.BoardVo;

@Repository
@Mapper
public interface BoardMapper {
	
	public List<BoardVo> selectBoard(BoardVo boardVo);
	
	public BoardVo selectBoardInfo(String id);
	
	public int insertBoard(BoardVo boardVo);
	
	public int updateBoard(BoardVo boardVo);
	
	public int deleteBoard(String id);
	
}

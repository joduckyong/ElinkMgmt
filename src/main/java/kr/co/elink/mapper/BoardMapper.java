package kr.co.elink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.elink.dto.BoardRVo;
import kr.co.elink.dto.BoardVo;
import kr.co.elink.dto.PrevNextVo;

@Repository
@Mapper
public interface BoardMapper {
	
	public List<BoardRVo> selectBoard(BoardVo boardVo);
	
	public BoardRVo selectBoardInfo(String id);
	
	public String selectBoardId(BoardVo boardVo);
	
	public int insertBoard(BoardVo boardVo);
	
	public int updateBoard(BoardVo boardVo);
	
	public int deleteBoard(String id);
	
	public int deleteBoardIds(BoardVo boardVo);
	
	public PrevNextVo selectPrevNextBoard(String id);
	
	
}

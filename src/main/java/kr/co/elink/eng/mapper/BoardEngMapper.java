package kr.co.elink.eng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.elink.dto.BoardRVo;
import kr.co.elink.dto.BoardVo;
import kr.co.elink.dto.PrevNextVo;

@Repository
@Mapper
public interface BoardEngMapper {
	
	public List<BoardRVo> selectEngBoard(BoardVo boardVo);
	
	public BoardRVo selectEngBoardInfo(String id);
	
	public String selectEngBoardId(BoardVo boardVo);
	
	public int insertEngBoard(BoardVo boardVo);
	
	public int updateEngBoard(BoardVo boardVo);
	
	public int deleteEngBoard(String id);
	
	public int deleteEngBoardIds(BoardVo boardVo);
	
	public PrevNextVo selectEngPrevNextBoard(String id);
	
	public String selectEngPinup();
	
	public int insertEngPinup(BoardVo boardVo);
	
	public int updateEngPinup(BoardVo boardVo);
	
	
}

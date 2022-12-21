package kr.co.elink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.elink.dto.BoardVo;
import kr.co.elink.dto.DefaultVo;

@Repository
@Mapper
public interface FileMapper {
	
	public List<DefaultVo> selectBoard(DefaultVo defaultVo);
	
	public BoardVo selectBoardInfo(String id);
	
	public int insertBoard(DefaultVo defaultVo);
	
	public int updateBoard(DefaultVo defaultVo);
	
	public int deleteBoard(String id);
	
}

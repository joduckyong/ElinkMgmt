package kr.co.elink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.elink.dto.OutlineRVo;
import kr.co.elink.dto.OutlineVo;

@Repository
@Mapper
public interface OutlineMapper {
	
	public List<OutlineRVo> selectOutline();
	
	public int insertOutline(OutlineVo outlineVo);
	
	public int updateOutline(OutlineVo outlineVo);
	
	public int deleteOutlineIds(OutlineVo outlineVo);

}

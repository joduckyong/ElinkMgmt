package kr.co.elink.eng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.elink.dto.OutlineRVo;
import kr.co.elink.dto.OutlineVo;

@Repository
@Mapper
public interface OutlineEngMapper {
	
	public List<OutlineRVo> selectEngOutline();
	
	public int insertEngOutline(OutlineVo outlineVo);
	
	public int updateEngOutline(OutlineVo outlineVo);
	
	public int deleteEngOutlineIds(OutlineVo outlineVo);

}

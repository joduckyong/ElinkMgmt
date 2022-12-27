package kr.co.elink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.elink.dto.ConactUsVo;

@Repository
@Mapper
public interface ConactUsMapper {
	
	public List<ConactUsVo> selectConactUs(ConactUsVo conactUsVo);
	
	public ConactUsVo selecConactUsInfo(String id);
	
	public int insertConactUs(ConactUsVo conactUsVo);
	
	public int updateConactUs(ConactUsVo conactUsVo);
	
	public int deleteConactUs(String id);
	
	public int deleteConactUsIds(ConactUsVo conactUsVo);
}

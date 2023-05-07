package kr.co.elink.eng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.elink.dto.ManagerRVo;
import kr.co.elink.dto.ManagerVo;

@Repository
@Mapper
public interface ManagerEngMapper {
	
	public List<ManagerRVo> selectEngManager(ManagerVo managerVo);
	
	public ManagerRVo selectEngManagerInfo(String id);
	
	public int insertEngManager(ManagerVo managerVo);
	
	public int updateEngManager(ManagerVo managerVo);

	public int deleteEngManager(String id);
	
	public int deleteEngManagerIds(ManagerVo managerVo);
	
	
	
}

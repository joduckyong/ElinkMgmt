package kr.co.elink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.elink.dto.ManagerRVo;
import kr.co.elink.dto.ManagerVo;

@Repository
@Mapper
public interface ManagerMapper {
	
	public List<ManagerRVo> selectManager(ManagerVo managerVo);
	
	public ManagerRVo selectManagerInfo(String id);
	
	public int insertManager(ManagerVo managerVo);
	
	public int updateManager(ManagerVo managerVo);

	public int deleteManager(String id);
	
	public int deleteManagerIds(ManagerVo managerVo);
	
	
	
}

package kr.co.elink.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.elink.dto.UserRVo;
import kr.co.elink.dto.UserVo;

@Repository
@Mapper
public interface UserMapper {
	
	public UserRVo selectUserInfo(UserVo userVo);
	
	public int insertUser(UserVo userVo);
	
}

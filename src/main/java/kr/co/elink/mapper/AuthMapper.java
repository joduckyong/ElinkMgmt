package kr.co.elink.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.elink.dto.AdminVo;

@Repository
@Mapper
public interface AuthMapper {
	
	public AdminVo selectAdmin(String id);
}

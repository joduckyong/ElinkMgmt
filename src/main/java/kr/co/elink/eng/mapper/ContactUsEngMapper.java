package kr.co.elink.eng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.elink.dto.ContactUsRVo;
import kr.co.elink.dto.ContactUsVo;

@Repository
@Mapper
public interface ContactUsEngMapper {
	
	public List<ContactUsRVo> selectEngContactUs(ContactUsVo contactUsVo);
	
	public ContactUsRVo selectEngContactUsInfo(ContactUsVo contactUsVo);
	
	public int insertEngContactUs(ContactUsVo contactUsVo);
	
	public int updateEngContactUs(ContactUsVo contactUsVo);
	
	public int deleteEngContactUs(String id);
	
	public int updateEngContactUsIds(ContactUsVo contactUsVo);
	
	public int deleteEngContactUsIds(ContactUsVo contactUsVo);
}

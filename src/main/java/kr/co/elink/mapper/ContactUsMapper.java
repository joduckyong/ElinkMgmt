package kr.co.elink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.elink.dto.ContactUsVo;

@Repository
@Mapper
public interface ContactUsMapper {
	
	public List<ContactUsVo> selectContactUs(ContactUsVo contactUsVo);
	
	public ContactUsVo selecContactUsInfo(String id);
	
	public int insertContactUs(ContactUsVo contactUsVo);
	
	public int updateContactUs(ContactUsVo contactUsVo);
	
	public int deleteContactUs(String id);
	
	public int deleteContactUsIds(ContactUsVo contactUsVo);
}

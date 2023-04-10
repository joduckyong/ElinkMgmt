package kr.co.elink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.elink.dto.BoardVo;
import kr.co.elink.dto.ManagerRVo;
import kr.co.elink.dto.ManagerVo;
import kr.co.elink.dto.OutlineRVo;
import kr.co.elink.dto.OutlineVo;
import kr.co.elink.mapper.ManagerMapper;
import kr.co.elink.mapper.OutlineMapper;

@Service
public class OutlineService {
	
	@Autowired
	OutlineMapper outlineMapper;
	
	
	public List<OutlineRVo> selectOutline(){
		return outlineMapper.selectOutline();
	}
	
	public int insertOutline(OutlineVo outlineVo){
		return outlineMapper.insertOutline(outlineVo);
	};
	
	public int updateOutline(OutlineVo outlineVo){
		return outlineMapper.updateOutline(outlineVo);
	};
	
	public int deleteOutlineIds(OutlineVo outlineVo) {
		return outlineMapper.deleteOutlineIds(outlineVo);
	}
	
	
}

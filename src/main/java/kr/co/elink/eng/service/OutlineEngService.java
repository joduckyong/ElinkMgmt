package kr.co.elink.eng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.elink.dto.OutlineRVo;
import kr.co.elink.dto.OutlineVo;
import kr.co.elink.eng.mapper.OutlineEngMapper;

@Service
public class OutlineEngService {
	
	@Autowired
	OutlineEngMapper outlineEngMapper;
	
	
	public List<OutlineRVo> selectEngOutline(){
		return outlineEngMapper.selectEngOutline();
	}
	
	public int insertEngOutline(OutlineVo outlineVo){
		return outlineEngMapper.insertEngOutline(outlineVo);
	};
	
	public int updateEngOutline(OutlineVo outlineVo){
		return outlineEngMapper.updateEngOutline(outlineVo);
	};
	
	public int deleteEngOutlineIds(OutlineVo outlineVo) {
		return outlineEngMapper.deleteEngOutlineIds(outlineVo);
	}
	
	
}

package kr.co.elink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.elink.dto.ConactUsVo;
import kr.co.elink.dto.PopupVo;
import kr.co.elink.mapper.ConactUsMapper;

@Service
public class ConactUsService {
	
	@Autowired
	ConactUsMapper conactUsMapper;
	
	public List<ConactUsVo> selectConactUs(String id, int pageIndex){
		
		// 페이징 처리
		ConactUsVo conactUsVo = new ConactUsVo();
		conactUsVo.setId(id);
		conactUsVo.setFirstIndex((pageIndex - 1) * conactUsVo.getRecordCountPerPage());
		conactUsVo.setLastIndex(conactUsVo.getRecordCountPerPage());		
		return conactUsMapper.selectConactUs(conactUsVo);
		
	};
	
	public ConactUsVo selecConactUsInfo(String id){
		return conactUsMapper.selecConactUsInfo(id);
	};
	
	@Transactional
	public int insertConactUs(ConactUsVo conactUsVo){
		return conactUsMapper.insertConactUs(conactUsVo);
	};
	
	@Transactional
	public int updateConactUs(ConactUsVo conactUsVo){
		return conactUsMapper.updateConactUs(conactUsVo);
	};
	
	@Transactional
	public int deleteConactUs(String id){
		return conactUsMapper.deleteConactUs(id);
	};
	
	@Transactional
	public int deleteConactUsIds(List<String> ids){
		return conactUsMapper.deleteConactUsIds(ids);
	}
}

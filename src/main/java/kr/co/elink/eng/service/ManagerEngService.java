package kr.co.elink.eng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.elink.dto.ManagerRVo;
import kr.co.elink.dto.ManagerVo;
import kr.co.elink.eng.mapper.ManagerEngMapper;

@Service
public class ManagerEngService {
	
	@Autowired
	ManagerEngMapper managerEngMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public List<ManagerRVo> selectEngManager(int pageIndex){
		// 페이징 처리
		ManagerVo managerVo = new ManagerVo();
		managerVo.setFirstIndex((pageIndex - 1) * managerVo.getRecordCountPerPage());
		managerVo.setLastIndex(managerVo.getRecordCountPerPage());
		
		return managerEngMapper.selectEngManager(managerVo);
	}
	
	public ManagerRVo selectEngManagerInfo(String id) {
		return managerEngMapper.selectEngManagerInfo(id);
	}
	
	public int insertEngManager(ManagerVo managerVo){
		String encodedPassword = passwordEncoder.encode(managerVo.getAdminPw());
		managerVo.setAdminPw(encodedPassword);
		return managerEngMapper.insertEngManager(managerVo);
	};
	
	public int updateEngManager(ManagerVo managerVo){
		String encodedPassword = passwordEncoder.encode(managerVo.getAdminPw());
		managerVo.setAdminPw(encodedPassword);
		
		return managerEngMapper.updateEngManager(managerVo);
	};
	
	public int deleteEngManagerIds(ManagerVo managerVo){
		
		return managerEngMapper.deleteEngManagerIds(managerVo);
	};
	
	
}

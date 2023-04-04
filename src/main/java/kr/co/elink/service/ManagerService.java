package kr.co.elink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.elink.dto.BoardVo;
import kr.co.elink.dto.ManagerRVo;
import kr.co.elink.dto.ManagerVo;
import kr.co.elink.mapper.ManagerMapper;

@Service
public class ManagerService {
	
	@Autowired
	ManagerMapper managerMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public List<ManagerRVo> selectManager(int pageIndex){
		// 페이징 처리
		ManagerVo managerVo = new ManagerVo();
		managerVo.setFirstIndex((pageIndex - 1) * managerVo.getRecordCountPerPage());
		managerVo.setLastIndex(managerVo.getRecordCountPerPage());
		
		return managerMapper.selectManager(managerVo);
	}
	
	public ManagerRVo selectManagerInfo(String id) {
		return managerMapper.selectManagerInfo(id);
	}
	
	public int insertManager(ManagerVo managerVo){
		String encodedPassword = passwordEncoder.encode(managerVo.getAdminPw());
		managerVo.setAdminPw(encodedPassword);
		return managerMapper.insertManager(managerVo);
	};
	
	public int updateManager(ManagerVo managerVo){
		
		return managerMapper.updateManager(managerVo);
	};
	
	public int deleteManagerIds(ManagerVo managerVo){
		
		return managerMapper.deleteManagerIds(managerVo);
	};
	
	
}

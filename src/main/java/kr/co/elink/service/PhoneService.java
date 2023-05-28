package kr.co.elink.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kcb.module.v3.OkCert;
import kcb.module.v3.exception.OkCertException;
import kcb.org.json.JSONObject;
import kr.co.elink.dto.UserRVo;
import kr.co.elink.dto.UserVo;
import kr.co.elink.mapper.UserMapper;

@Service
public class PhoneService {
	
	@Value("${kcb_module.license}")
	private String kcbModuleLicense;
	
	@Value("${kcb_module.cp_cd}")
	private String kcbModuleCpCd;
	
	@Value("${kcb_module.site_name}")
	private String kcbModuleSiteName;
	
	@Value("${kcb_module.site_url}")
	private String kcbSiteUrl;
	
	@Value("${kcb_module.add.url}")
	private String kcbReturnAddUrl;
	
	@Value("${kcb_module.view.url}")
	private String kcbReturnViewUrl;
	
	@Value("${encrypt.key}")
	private String encryptKey;
	
	@Autowired
	UserMapper userMapper;
	
	private String target = "PROD";
	
	public String phonePost2(HttpServletRequest request) throws OkCertException{

		String TYPE = request.getParameter("type");
		String RETURN_URL = kcbReturnViewUrl;
		
		if("I".equals(TYPE)) {
			RETURN_URL = kcbReturnAddUrl;
		}
		
		String SITE_NAME = kcbModuleSiteName; 		
		String SITE_URL = kcbSiteUrl;
		String RQST_CAUS_CD = "00";
		String svcName = "IDS_HS_POPUP_START";
		
		JSONObject reqJson = new JSONObject();
		reqJson.put("RETURN_URL", RETURN_URL);
		reqJson.put("SITE_NAME", SITE_NAME);
		reqJson.put("SITE_URL", SITE_URL);
		reqJson.put("RQST_CAUS_CD", RQST_CAUS_CD);
		
		String reqStr = reqJson.toString();
		OkCert okcert = new OkCert();
		String resultStr = okcert.callOkCert(target, kcbModuleCpCd, svcName, kcbModuleLicense,  reqStr);
		
		return resultStr;
	};
	
	public String phonePost3(HttpServletRequest request) throws OkCertException{
		
		String MDL_TKN = request.getParameter("mdl_tkn");
		String svcName = "IDS_HS_POPUP_RESULT";
		
		/**************************************************************************
		okcert3 요청 정보
		**************************************************************************/
		JSONObject reqJson = new JSONObject();
		reqJson.put("MDL_TKN", MDL_TKN);
		
		String reqStr = reqJson.toString();
		OkCert okcert = new OkCert();
		String resultStr = okcert.callOkCert(target, kcbModuleCpCd, svcName, kcbModuleLicense,  reqStr);
		
		return resultStr;
	};
	
	public UserRVo selectUserInfo(String id){
		
		UserVo userVo = new UserVo();
		userVo.setId(id);
		userVo.setEncryptKey(encryptKey);
		return userMapper.selectUserInfo(userVo);
	};
	
	@Transactional
	public int insertUser(UserVo userVo) throws IOException{
		
		userVo.setEncryptKey(encryptKey);
		int result = userMapper.insertUser(userVo);
		
		return result;
	};
	
	@Transactional
	public int updateUser(UserVo userVo) throws IOException{
		
		userVo.setEncryptKey(encryptKey);
		int result = userMapper.updateUser(userVo);
		
		return result;
	};
	
}

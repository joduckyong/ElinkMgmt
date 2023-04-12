package kr.co.elink.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kcb.org.json.JSONObject;
import kr.co.elink.dto.UserVo;
import kr.co.elink.service.PhoneService;

@Controller
@RequestMapping("/api/phone/")
public class PhoneController {
	
	@Value("${kcb_module.cp_cd}")
	private String kcbModuleCpCd;
	
	@Autowired
	PhoneService phoneService;
	
//	@GetMapping("{page}")
//	public String phoneGet(@PathVariable String page, ModelMap model) throws Exception{
//		
//		return "phone/"+page;
//	}	
//	
//	@PostMapping("{page}")
//	public String phonePost(@PathVariable String page, ModelMap model) throws Exception{
//		
//		return "phone/"+page;
//	}	
	
	@GetMapping("popup1")
	public String phoneGet(ModelMap model) throws Exception{
		
		return "phone/popup1";
	}	
	
	@GetMapping("popup2")
	public String phoneGet2(HttpServletRequest request, ModelMap model) throws Exception{
		
		String popupUrl = "https://safe.ok-name.co.kr/CommonSvl";// 운영 URL
		
		String resultStr = phoneService.phonePost2(request);
		
		JSONObject resJson = new JSONObject(resultStr);
	    String RSLT_CD =  resJson.getString("RSLT_CD");
	    String RSLT_MSG = resJson.getString("RSLT_MSG");
	    String MDL_TKN = "";
	    
		boolean succ = false;
	    if ("B000".equals(RSLT_CD) && resJson.has("MDL_TKN") ) {
			MDL_TKN = resJson.getString("MDL_TKN");
			succ = true;
	    }
	    
	    model.addAttribute("MDL_TKN", MDL_TKN);
	    model.addAttribute("CP_CD", kcbModuleCpCd);
	    model.addAttribute("SUCC", succ);
	    
	    model.addAttribute("RSLT_CD", RSLT_CD);
	    model.addAttribute("RSLT_MSG", RSLT_MSG);
	    
	    model.addAttribute("POPUP_URL", popupUrl);
	    
		return "phone/popup2";
	}		
	
	@GetMapping("popup3")
	public String phoneGet3(HttpServletRequest request, ModelMap model) throws Exception{
		
		String resultStr = phoneService.phonePost3(request);
		JSONObject resJson = new JSONObject(resultStr);
	    String RSLT_CD =  resJson.getString("RSLT_CD");
	    String RSLT_MSG =  resJson.getString("RSLT_MSG");
		String TX_SEQ_NO =  resJson.getString("TX_SEQ_NO");
		
		String RSLT_NAME = "";
		String RSLT_BIRTHDAY = "";
		String RSLT_SEX_CD = "";
		String RSLT_NTV_FRNR_CD = "";
		
		String DI = "";
		String CI = "";
		String CI_UPDATE = "";
		String TEL_COM_CD = "";
		String TEL_NO = "";
		
		String RETURN_MSG= "";
		if(resJson.has("RETURN_MSG")) RETURN_MSG =  resJson.getString("RETURN_MSG");
		
		if ("B000".equals(RSLT_CD)){
			RSLT_NAME = resJson.getString("RSLT_NAME");
			RSLT_BIRTHDAY = resJson.getString("RSLT_BIRTHDAY");
			RSLT_SEX_CD = resJson.getString("RSLT_SEX_CD");
			RSLT_NTV_FRNR_CD = resJson.getString("RSLT_NTV_FRNR_CD");
			
			DI = resJson.getString("DI");
			CI = resJson.getString("CI");
			CI_UPDATE = resJson.getString("CI_UPDATE");
			TEL_COM_CD = resJson.getString("TEL_COM_CD");
			TEL_NO = resJson.getString("TEL_NO");
			
			UserVo userVo = new UserVo();
			
			userVo.setCi(CI);
			userVo.setTelno(TEL_NO);
			userVo.setTelcom(TEL_COM_CD);
			userVo.setGender(RSLT_SEX_CD);
			userVo.setBrth(RSLT_BIRTHDAY);
			phoneService.insertUser(userVo);
		}
		
		
		model.addAttribute("CP_CD", kcbModuleCpCd);
		model.addAttribute("TX_SEQ_NO", TX_SEQ_NO);
		model.addAttribute("RSLT_CD", RSLT_CD);
		model.addAttribute("RSLT_MSG", RSLT_MSG);
		model.addAttribute("RSLT_NAME", RSLT_NAME);
		model.addAttribute("RSLT_BIRTHDAY", RSLT_BIRTHDAY);
		model.addAttribute("RSLT_SEX_CD", RSLT_SEX_CD);
		model.addAttribute("RSLT_NTV_FRNR_CD", RSLT_NTV_FRNR_CD);
		model.addAttribute("DI", DI);
		model.addAttribute("CI", CI);
		model.addAttribute("CI_UPDATE", CI_UPDATE);
		model.addAttribute("TEL_COM_CD", TEL_COM_CD);
		model.addAttribute("TEL_NO", TEL_NO);
		model.addAttribute("RETURN_MSG", RETURN_MSG);
		
		return "phone/popup3";
	}		
	
	
}
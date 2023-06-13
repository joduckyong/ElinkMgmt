package kr.co.elink.ev.controller;

import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.elink.common.ApiService;
import kr.co.elink.common.StatusEnum;
import kr.co.elink.common.util.AES256;
import kr.co.elink.dto.MessageVo;

@Controller
@RequestMapping("/api/ev/common")
public class EVController {
	
	@Value("${ev.api.url}")
    private String evApiUrl;
	
	@Autowired
	private ApiService<?> apiService;

	@PostMapping("")
	public ResponseEntity<MessageVo> selectEv(@RequestBody Map<String, Object> param, @RequestHeader String accessEvToken, HttpSession session, ModelMap model) {
		
		String url = evApiUrl+param.get("url");
		String userNo = (String) param.get("userNo");
		String urlType = (String) param.get("urlType");
		
		if(urlType != null && "none".equals(urlType)) {
			url = (String) param.get("url");
		}
		//마이페이지 > 이용내역 사용자 번호
		if(userNo != null && !"".equals(userNo)) {
			param.put("userNo", AES256.decrypt(userNo));
		}
		
		ResponseEntity<?> responseEntity = apiService.post(url, param, accessEvToken);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		MessageVo message = MessageVo.builder()
				.status(StatusEnum.OK)
				.message("성공 코드")
				.data(responseEntity.getBody())
				.build();
		
		return new ResponseEntity<>(message, headers, HttpStatus.OK);
		
	}
}

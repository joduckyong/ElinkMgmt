package kr.co.elink.ev.controller;

import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.elink.common.ApiService;
import kr.co.elink.common.StatusEnum;
import kr.co.elink.common.util.AES256;
import kr.co.elink.dto.MessageVo;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ev/auth")
public class EVAuthController {

	@Value("${ev.api.url}")
	private String evApiUrl;

	@Autowired
	private ApiService<?> apiService;

	private ObjectMapper objectMapper;
	 
	/*
	 * EV 인증
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@PostMapping("")
	public ResponseEntity<MessageVo> auth(@RequestBody Map<String, Object> param, HttpSession session, ModelMap model)
			throws Exception {

		String url = evApiUrl + param.get("url");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("username", param.get("username"));
		body.add("password", param.get("password"));
		body.add("grant_type", param.get("grant_type"));
		body.add("scope", param.get("scope"));
		body.add("login_type", param.get("login_type"));

		ResponseEntity<?> responseEntity = apiService.postAuth(url, body);
		
		objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(responseEntity.getBody());
		JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
        jsonObject.replace("USER_NO", AES256.encrypt((String) jsonObject.get("USER_NO")));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		MessageVo message = MessageVo.builder().status(StatusEnum.OK).message("성공 코드").data(jsonObject)
				.build();

		return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}

	/*
	 * 공통 값
	 */
	@ResponseBody
	@PostMapping("{apiUser}")
	public ResponseEntity<MessageVo> apiUser(@RequestBody Map<String, Object> param, HttpSession session,
			ModelMap model) throws Exception {

		String url = evApiUrl + param.get("url");

		ResponseEntity<?> responseEntity = apiService.post(url, param);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		MessageVo message = MessageVo.builder().status(StatusEnum.OK).message("성공 코드").data(responseEntity.getBody())
				.build();

		return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}

	/*
	 * SNS 인증
	 */
	@ResponseBody
	@PostMapping("/token/{apiId}")
	public ResponseEntity<MessageVo> authToken(@RequestBody Map<String, Object> param, HttpSession session,
			ModelMap model) throws Exception {

		String url = (String) param.get("url");

		ResponseEntity<?> responseEntity = apiService.post(url, param);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		MessageVo message = MessageVo.builder().status(StatusEnum.OK).message("성공 코드").data(responseEntity.getBody())
				.build();

		return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}

}
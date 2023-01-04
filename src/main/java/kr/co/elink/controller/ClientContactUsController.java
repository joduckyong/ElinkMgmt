package kr.co.elink.controller;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.elink.common.StatusEnum;
import kr.co.elink.dto.ContactUsVo;
import kr.co.elink.dto.MessageVo;
import kr.co.elink.service.ContactUsService;

@RestController
@RequestMapping("/api/client/contactUs")
public class ClientContactUsController {

	@Autowired
	ContactUsService contactUsService;
	
    
    @PostMapping("")
    public ResponseEntity<MessageVo> insertContactUs(@RequestPart ContactUsVo contactUsVo
    		, @RequestPart(value="file", required = false) MultipartFile multipartFile) throws IOException  {
    	int insertContactUs = contactUsService.insertContactUs(contactUsVo, multipartFile);
    	
    	HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(insertContactUs)
            	.build();
    	
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    	
    }    
    
}

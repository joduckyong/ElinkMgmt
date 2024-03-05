package kr.co.elink.client.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.elink.common.StatusEnum;
import kr.co.elink.dto.BoardRVo;
import kr.co.elink.dto.ContactUsRVo;
import kr.co.elink.dto.ContactUsVo;
import kr.co.elink.dto.FileVo;
import kr.co.elink.dto.MessageVo;
import kr.co.elink.dto.PrevNextVo;
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
    
    @PostMapping("/cnt")
    public ResponseEntity<MessageVo> selectContactUsInfoCnt(@RequestBody ContactUsVo contactUsVo) {
    	String selectContactUsInfoCnt = contactUsService.selectContactUsInfoCnt(contactUsVo);
        
    	HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.totalCount(1)
            	.data(selectContactUsInfoCnt)
            	.build();
        
    	return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @PostMapping("/list")
    public ResponseEntity<MessageVo> selectContactUs(@RequestBody ContactUsVo contactUsVo) {
    	
    	List<ContactUsRVo> list = null;
    	int totalCount = 0;
    	
    	if(contactUsVo.getContactMail() != null && !"".equals(contactUsVo.getContactMail())) {
    		list = contactUsService.selectClientContactUs(contactUsVo);
    		if(list.size() > 0) {
    			totalCount = list.get(0).getTotalCount();
    		}
    	}
        
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
        	.status(StatusEnum.OK)
        	.message("성공 코드")
        	.totalCount(totalCount)
        	.data(list)
        	.build();
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }    
    
    @PostMapping("/inquiryInfo")
    public ResponseEntity<MessageVo> selectContactUsInfo(@RequestBody ContactUsVo contactUsVo) {
    	
    	ContactUsRVo selectContactUsInfo = contactUsService.selectContactUsInfo(contactUsVo.getContactId());
    	PrevNextVo selectPrevNextContactUs = contactUsService.selectPrevNextContactUs(contactUsVo);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.totalCount(1)
            	.data(selectContactUsInfo)
            	.prevNextData(selectPrevNextContactUs)
            	.build();
        
    	return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
}

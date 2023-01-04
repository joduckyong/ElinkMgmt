package kr.co.elink.controller;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.elink.common.StatusEnum;
import kr.co.elink.dto.ContactUsVo;
import kr.co.elink.dto.MessageVo;
import kr.co.elink.service.ContactUsService;

@RestController
@RequestMapping("/api/contactUs")
public class ContactUsController {

	@Autowired
	ContactUsService contactUsService;
	
	@GetMapping("/{id}/{pageIndex}")
    public ResponseEntity<MessageVo> selectContactUs(@PathVariable("id") String id, @PathVariable("pageIndex") int pageIndex) {
        List<ContactUsVo> selectContactUs = contactUsService.selectContactUs(id, pageIndex);
        
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
        	.status(StatusEnum.OK)
        	.message("성공 코드")
//        	.totalCount(totalCount)
        	.data(selectContactUs)
        	.build();
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MessageVo> selecContactUsInfo(@PathVariable("id") String id) {
    	ContactUsVo selecContactUsInfo = contactUsService.selecContactUsInfo(id);
        
    	HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.totalCount(1)
            	.data(selecContactUsInfo)
//            	.files(selectFileList)
            	.build();
        
    	return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageVo> deleteContactUs(@PathVariable("id") String id) {
    	int deleteContactUs = contactUsService.deleteContactUs(id);
        
    	HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(deleteContactUs)
            	.build();
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
	@DeleteMapping("")
	public ResponseEntity<MessageVo> deleteContactUsIds(@RequestBody ContactUsVo contactUsVo) throws Exception{
    	int deleteContactUsIds = contactUsService.deleteContactUsIds(contactUsVo);
        
    	 HttpHeaders headers= new HttpHeaders();
         headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

         MessageVo message = MessageVo.builder()
             	.status(StatusEnum.OK)
             	.message("성공 코드")
             	.data(deleteContactUsIds)
             	.build();
         
         return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}
}

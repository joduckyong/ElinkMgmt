package kr.co.elink.admin.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.elink.common.StatusEnum;
import kr.co.elink.dto.ContactUsRVo;
import kr.co.elink.dto.ContactUsVo;
import kr.co.elink.dto.FileVo;
import kr.co.elink.dto.MessageVo;
import kr.co.elink.service.ContactUsService;
import kr.co.elink.service.FileService;

@RestController
@RequestMapping("/api/contactUs")
public class ContactUsController {

	@Autowired
	ContactUsService contactUsService;
	
	@Autowired
	FileService fileService;
	
	@GetMapping({"/{id}/{contactType}/{pageIndex}/{searchKeyword}", "/{id}/{contactType}/{pageIndex}/{searchKeyword}/{searchCondition}", "/{id}/{contactType}/{pageIndex}"})
    public ResponseEntity<MessageVo> selectContactUs(
    		@PathVariable("id") String id
    		, @PathVariable("contactType") String contactType
    		, @PathVariable("pageIndex") int pageIndex
    		, @PathVariable(name="searchCondition", required=false) String searchCondition
    		, @PathVariable(name="searchKeyword", required=false) String searchKeyword
    	) {
        List<ContactUsRVo> list = contactUsService.selectContactUs(id, contactType, pageIndex, searchKeyword, searchCondition);
        int totalCount = 0;
        if(list.size() > 0) {
        	totalCount = list.get(0).getTotalCount();
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
    
    @GetMapping("/{id}")
    public ResponseEntity<MessageVo> selectContactUsInfo(@PathVariable("id") String id) {
    	ContactUsRVo selectContactUsInfo = contactUsService.selectContactUsInfo(id);
    	List<FileVo> selectFileList = fileService.selectFileList(id);
        
    	HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.totalCount(1)
            	.data(selectContactUsInfo)
            	.files(selectFileList)
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
    
	@PostMapping("")
	public ResponseEntity<MessageVo> updateContactUsIds(@RequestBody ContactUsVo contactUsVo) throws Exception{
    	int updateContactUsIds = contactUsService.updateContactUsIds(contactUsVo);
        
    	 HttpHeaders headers= new HttpHeaders();
         headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

         MessageVo message = MessageVo.builder()
             	.status(StatusEnum.OK)
             	.message("성공 코드")
             	.data(updateContactUsIds)
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

package kr.co.elink.admin.eng.controller;

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
import kr.co.elink.eng.service.ContactUsEngService;
import kr.co.elink.service.FileService;

@RestController
@RequestMapping("/en/api/contactUs")
public class ContactUsEngController {

	@Autowired
	ContactUsEngService contactUsEngService;
	
	@Autowired
	FileService fileService;
	
	@GetMapping({"/{id}/{contactType}/{pageIndex}/{searchKeyword}", "/{id}/{contactType}/{pageIndex}/{searchKeyword}/{searchCondition}", "/{id}/{contactType}/{pageIndex}"})
    public ResponseEntity<MessageVo> selectEngContactUs(
    		@PathVariable("id") String id
    		, @PathVariable("contactType") String contactType
    		, @PathVariable("pageIndex") int pageIndex
    		, @PathVariable(name="searchCondition", required=false) String searchCondition
    		, @PathVariable(name="searchKeyword", required=false) String searchKeyword
    	) {
        List<ContactUsRVo> list = contactUsEngService.selectEngContactUs(id, contactType, pageIndex, searchKeyword, searchCondition);
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
    public ResponseEntity<MessageVo> selectEngContactUsInfo(@PathVariable("id") String id) {
    	ContactUsRVo selecContactUsInfo = contactUsEngService.selectEngContactUsInfo(id);
    	List<FileVo> selectFileList = fileService.selectFileList(id);
        
    	HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.totalCount(1)
            	.data(selecContactUsInfo)
            	.files(selectFileList)
            	.build();
        
    	return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageVo> deleteEngContactUs(@PathVariable("id") String id) {
    	int deleteContactUs = contactUsEngService.deleteEngContactUs(id);
        
    	HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(deleteContactUs)
            	.build();
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
	@PostMapping("/update")
	public ResponseEntity<MessageVo> updateEngContactUs(@RequestBody ContactUsVo contactUsVo) throws Exception{
		int updateContactUsIds = contactUsEngService.updateEngContactUs(contactUsVo);
		
		HttpHeaders headers= new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		MessageVo message = MessageVo.builder()
				.status(StatusEnum.OK)
				.message("성공 코드")
				.data(updateContactUsIds)
				.build();
		
		return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}
	
    @PostMapping("")
	public ResponseEntity<MessageVo> updateEngContactUsIds(@RequestBody ContactUsVo contactUsVo) throws Exception{
    	int updateEngContactUsIds = contactUsEngService.updateEngContactUsIds(contactUsVo);
        
    	 HttpHeaders headers= new HttpHeaders();
         headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

         MessageVo message = MessageVo.builder()
             	.status(StatusEnum.OK)
             	.message("성공 코드")
             	.data(updateEngContactUsIds)
             	.build();
         
         return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}
	
	@DeleteMapping("")
	public ResponseEntity<MessageVo> deleteEngContactUsIds(@RequestBody ContactUsVo contactUsVo) throws Exception{
		int deleteContactUsIds = contactUsEngService.deleteEngContactUsIds(contactUsVo);
		
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

package kr.co.elink.admin.eng.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.elink.common.StatusEnum;
import kr.co.elink.dto.MessageVo;
import kr.co.elink.dto.OutlineRVo;
import kr.co.elink.dto.OutlineVo;
import kr.co.elink.eng.service.OutlineEngService;

@RestController
@RequestMapping("/en/api/company/outline")
public class OutlineEngController {

	@Autowired
	OutlineEngService outlineEngService;
	
    @GetMapping("")
    public ResponseEntity<MessageVo> selectEngOutline() {
        List<OutlineRVo> list = outlineEngService.selectEngOutline();
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
    
    @PostMapping("")
    public ResponseEntity<MessageVo> insertEngOutline(@RequestBody OutlineVo outlineVo) throws IOException {
    	
    	int insertOutline = outlineEngService.insertEngOutline(outlineVo);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(insertOutline)
            	.build();
    	
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @PostMapping("/update")
    public ResponseEntity<MessageVo> updateEngOutline(@RequestBody OutlineVo outlineVo) throws IOException {
    	
    	int updateOutline = outlineEngService.updateEngOutline(outlineVo);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(updateOutline)
            	.build();
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @DeleteMapping("")
	public ResponseEntity<MessageVo> deleteEngOutlineIds(@RequestBody OutlineVo outlineVo) throws Exception{
    	int deleteOutlineIds = outlineEngService.deleteEngOutlineIds(outlineVo);
        
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(deleteOutlineIds)
            	.build();
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}
    
    
}

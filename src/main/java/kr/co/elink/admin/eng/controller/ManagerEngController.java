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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.elink.common.StatusEnum;
import kr.co.elink.dto.ManagerRVo;
import kr.co.elink.dto.ManagerVo;
import kr.co.elink.dto.MessageVo;
import kr.co.elink.eng.service.ManagerEngService;

@RestController
@RequestMapping("/en/api/manager")
public class ManagerEngController {

	@Autowired
	ManagerEngService managerEngService;
	
    @GetMapping("/list/{pageIndex}")
    public ResponseEntity<MessageVo> selectEngManager(
    		@PathVariable("pageIndex") int pageIndex
    	) {
        List<ManagerRVo> list = managerEngService.selectEngManager(pageIndex);
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
    public ResponseEntity<MessageVo> selectEngManagerInfo(@PathVariable("id") String id) {
    	ManagerRVo selectManagerInfo = managerEngService.selectEngManagerInfo(id);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.totalCount(1)
            	.data(selectManagerInfo)
            	.build();
        
    	return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @PostMapping("")
    public ResponseEntity<MessageVo> insertEngManager(@RequestBody ManagerVo managerVo) throws IOException {
    	
    	int insertManager = managerEngService.insertEngManager(managerVo);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(insertManager)
            	.build();
    	
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @PostMapping("/update")
    public ResponseEntity<MessageVo> updateEngManager(@RequestBody ManagerVo managerVo) throws IOException {
    	
    	int updateManager = managerEngService.updateEngManager(managerVo);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(updateManager)
            	.build();
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @DeleteMapping("")
	public ResponseEntity<MessageVo> deleteEngManagerIds(@RequestBody ManagerVo managerVo) throws Exception{
    	int deleteManagerIds = managerEngService.deleteEngManagerIds(managerVo);
        
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(deleteManagerIds)
            	.build();
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}
    
    
}

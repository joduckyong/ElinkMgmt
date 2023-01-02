package kr.co.elink.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.elink.common.StatusEnum;
import kr.co.elink.dto.MessageVo;
import kr.co.elink.dto.PopupRVo;
import kr.co.elink.dto.PopupVo;
import kr.co.elink.service.PopupService;

@RestController
@RequestMapping("/api/popup")
public class PopupController {

	@Autowired
	PopupService popupService;
	
	@GetMapping("/{id}/{pageIndex}")
    public ResponseEntity<MessageVo> selectPopup(@PathVariable("id") String id, @PathVariable("pageIndex") int pageIndex) {
        List<PopupRVo> list = popupService.selectPopup(id, pageIndex);
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
    public ResponseEntity<MessageVo> selectPopupInfo(@PathVariable("id") String id) {
    	PopupRVo selectPopupInfo = popupService.selectPopupInfo(id);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.totalCount(1)
            	.data(selectPopupInfo)
            	.files(new ArrayList<>())
            	.build();
        
    	return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @PostMapping("/")
    public ResponseEntity<MessageVo> insertPopup(@RequestPart PopupVo popupVo, @RequestPart(value="thumbnail", required = false) MultipartFile multipartThumbnail) throws IOException {
    	int insertPopup = popupService.insertPopup(popupVo, multipartThumbnail);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(insertPopup)
            	.build();
    	
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }    

    @PostMapping("/update")
    public ResponseEntity<MessageVo> updatePopup(@RequestPart PopupVo popupVo
    		, @RequestPart(value="thumbnail", required = false) MultipartFile multipartThumbnail) throws IOException {
    	
    	int updatePopup = popupService.updatePopup(popupVo, multipartThumbnail);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(updatePopup)
            	.build();
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
//    @PutMapping("")
//    public ResponseEntity<MessageVo> updatePopup(@RequestBody PopupVo popupVo) {
//    	int updatePopup = popupService.updatePopup(popupVo);
//    	
//        HttpHeaders headers= new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//
//        MessageVo message = MessageVo.builder()
//            	.status(StatusEnum.OK)
//            	.message("성공 코드")
//            	.data(updatePopup)
//            	.build();
//        
//        return new ResponseEntity<>(message, headers, HttpStatus.OK);
//    }    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageVo> deletePopup(@PathVariable("id") String id) {
    	int deletePopup = popupService.deletePopup(id);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(deletePopup)
            	.build();
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
	@DeleteMapping("")
	public ResponseEntity<MessageVo> deletePopupIds(@RequestBody PopupVo popupVo) throws Exception{
    	int deletePopupIds = popupService.deletePopupIds(popupVo);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(deletePopupIds)
            	.build();
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);	
	}
}

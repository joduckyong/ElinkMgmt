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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.elink.common.StatusEnum;
import kr.co.elink.dto.BoardRVo;
import kr.co.elink.dto.BoardVo;
import kr.co.elink.dto.FileVo;
import kr.co.elink.dto.MessageVo;
import kr.co.elink.eng.service.BoardEngService;
import kr.co.elink.service.FileService;

@RestController
@RequestMapping("/en/api/board")
public class BoardEngController {

	@Autowired
	BoardEngService boardEngService;
	
	@Autowired
	FileService fileService;
	
    @GetMapping({"/{id}/{pageIndex}/{searchKeyword}", "/{id}/{pageIndex}/{searchKeyword}/{searchCondition}", "/{id}/{pageIndex}"})
    public ResponseEntity<MessageVo> selectEngBoard(
    		@PathVariable("id") String id
    		, @PathVariable("pageIndex") int pageIndex
    		, @PathVariable(name="searchCondition", required=false) String searchCondition
    		, @PathVariable(name="searchKeyword", required=false) String searchKeyword
    	) {
        List<BoardRVo> list = boardEngService.selectEngBoard(id, pageIndex, searchKeyword, searchCondition);
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
    public ResponseEntity<MessageVo> selectEngBoardInfo(@PathVariable("id") String id) {
    	BoardRVo selectBoardInfo = boardEngService.selectEngBoardInfo(id);
    	List<FileVo> selectFileList = fileService.selectFileList(id);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.totalCount(1)
            	.data(selectBoardInfo)
            	.files(selectFileList)
            	.build();
        
    	return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @PostMapping("/")
    public ResponseEntity<MessageVo> insertEngBoard(@RequestPart BoardVo boardVo
    		, @RequestPart(value="thumbnail", required = false) MultipartFile multipartThumbnail
    		, @RequestPart(value="file", required = false) MultipartFile multipartFile
    		, @RequestPart(value="files", required = false) List<MultipartFile> multipartFiles) throws IOException {
    	
    	int insertBoard = boardEngService.insertEngBoard(boardVo, multipartThumbnail, multipartFile, multipartFiles);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(insertBoard)
            	.build();
    	
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @PostMapping("/update")
    public ResponseEntity<MessageVo> updateEngBoard(@RequestPart BoardVo boardVo
    		, @RequestPart(value="thumbnail", required = false) MultipartFile multipartThumbnail
    		, @RequestPart(value="file", required = false) MultipartFile multipartFile
    		, @RequestPart(value="files", required = false) List<MultipartFile> multipartFiles) throws IOException {
    	
    	int updateBoard = boardEngService.updateEngBoard(boardVo, multipartThumbnail, multipartFile, multipartFiles);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(updateBoard)
            	.build();
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageVo> deleteEngBoard(@PathVariable("id") String id) {
    	int deleteBoard = boardEngService.deleteEngBoard(id);
        
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(deleteBoard)
            	.build();
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
	@DeleteMapping("")
	public ResponseEntity<MessageVo> deleteEngBoardIds(@RequestBody BoardVo boardVo) throws Exception{
    	int deleteBoardIds = boardEngService.deleteEngBoardIds(boardVo);
        
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(deleteBoardIds)
            	.build();
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}
	
	@GetMapping("/pinup")
    public ResponseEntity<MessageVo> selectEngPinup() {
    	String selectPinup = boardEngService.selectEngPinup();
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.totalCount(1)
            	.pinupData(selectPinup)
            	.build();
        
    	return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
	
	@PostMapping("/update/pinup")
    public ResponseEntity<MessageVo> updateEngPinup(@RequestBody BoardVo boardVo){
    	
    	int updatePinup = boardEngService.updateEngPinup(boardVo);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(updatePinup)
            	.build();
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
}

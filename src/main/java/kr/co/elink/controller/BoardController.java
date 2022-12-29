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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.elink.common.StatusEnum;
import kr.co.elink.dto.BoardRVo;
import kr.co.elink.dto.BoardVo;
import kr.co.elink.dto.MessageVo;
import kr.co.elink.service.BoardService;

@RestController
@RequestMapping("/api/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
    @GetMapping({"/{id}/{pageIndex}/{searchKeyword}", "/{id}/{pageIndex}/{searchKeyword}/{searchCondition}", "/{id}/{pageIndex}"})
    public ResponseEntity<MessageVo> selectBoard(
    		@PathVariable("id") String id
    		, @PathVariable("pageIndex") int pageIndex
    		, @PathVariable(name="searchCondition", required=false) String searchCondition
    		, @PathVariable(name="searchKeyword", required=false) String searchKeyword
    	) {
        List<BoardRVo> list = boardService.selectBoard(id, pageIndex, searchKeyword, searchCondition);
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
    public ResponseEntity<MessageVo> selectBoardInfo(@PathVariable("id") String id) {
    	BoardRVo selectBoardInfo = boardService.selectBoardInfo(id);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.totalCount(1)
            	.data(selectBoardInfo)
            	.files(new ArrayList<>())
            	.build();
        
    	return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @PostMapping("")
    public ResponseEntity<MessageVo> insertBoard(@RequestBody BoardVo boardVo) {
    	int insertBoard = boardService.insertBoard(boardVo);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(insertBoard)
            	.build();
    	
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }  
    
    @PostMapping("/test")
    public ResponseEntity<MessageVo> insertBoard2(@RequestPart BoardVo boardVo, @RequestPart(value="thumbnail", required = false) MultipartFile multipartThumbnail, @RequestPart(value="file", required = false) MultipartFile multipartFile) throws IOException {
    	
    	int insertBoard = boardService.insertBoard(boardVo, multipartThumbnail, multipartFile);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(insertBoard)
            	.build();
    	
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @PutMapping("")
    public ResponseEntity<MessageVo> updateBoard(@RequestBody BoardVo boardVo) {
    	int updateBoard = boardService.updateBoard(boardVo);
    	
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
    public ResponseEntity<MessageVo> deleteBoard(@PathVariable("id") String id) {
    	int deleteBoard = boardService.deleteBoard(id);
        
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
	public ResponseEntity<MessageVo> deleteBoardIds(@RequestBody BoardVo boardVo) throws Exception{
    	int deleteBoardIds = boardService.deleteBoardIds(boardVo);
        
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.data(deleteBoardIds)
            	.build();
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}	    
    
}

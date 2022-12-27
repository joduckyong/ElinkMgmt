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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.elink.common.StatusEnum;
import kr.co.elink.dto.BoardVo;
import kr.co.elink.dto.MessageVo;
import kr.co.elink.service.BoardService;

@RestController
@RequestMapping("/api/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
    @GetMapping("/{id}/{pageIndex}/{searchKeyword}")
    public ResponseEntity<MessageVo> selectBoard(@PathVariable("id") String id, @PathVariable("pageIndex") int pageIndex, @PathVariable("searchKeyword") String searchKeyword) {
        List<BoardVo> list = boardService.selectBoard(id, pageIndex, searchKeyword);
        
        MessageVo message = new MessageVo();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(list);
        
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MessageVo> selectBoardInfo(@PathVariable("id") String id) {
    	BoardVo selectBoardInfo = boardService.selectBoardInfo(id);
    	
    	MessageVo message = new MessageVo();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(selectBoardInfo);
    	
    	return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @PostMapping("")
    public ResponseEntity<MessageVo> insertBoard(@RequestBody BoardVo boardVo) {
    	int insertBoard = boardService.insertBoard(boardVo);
    	
    	MessageVo message = new MessageVo();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(insertBoard);
    	
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }    
    
    @PutMapping("")
    public ResponseEntity<MessageVo> updateBoard(@RequestBody BoardVo boardVo) {
    	int updateBoard = boardService.updateBoard(boardVo);
    	
    	MessageVo message = new MessageVo();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(updateBoard);
    	
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageVo> deleteBoard(@PathVariable("id") String id) {
    	int deleteBoard = boardService.deleteBoard(id);
        
    	MessageVo message = new MessageVo();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(deleteBoard);
    	
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
	@DeleteMapping("")
	public ResponseEntity<MessageVo> deleteBoardIds(@RequestBody BoardVo boardVo) throws Exception{
    	int deleteBoardIds = boardService.deleteBoardIds(boardVo);
        
    	MessageVo message = new MessageVo();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(deleteBoardIds);
    	
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}	    
    
}

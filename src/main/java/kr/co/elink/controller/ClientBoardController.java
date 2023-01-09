package kr.co.elink.controller;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.elink.common.StatusEnum;
import kr.co.elink.dto.BoardRVo;
import kr.co.elink.dto.FileVo;
import kr.co.elink.dto.MessageVo;
import kr.co.elink.dto.PrevNextVo;
import kr.co.elink.service.BoardService;
import kr.co.elink.service.FileService;

@RestController
@RequestMapping("/api/client/board")
public class ClientBoardController {

	@Autowired
	BoardService boardService;
	
	@Autowired
	FileService fileService;
	
    @GetMapping({"/{id}/{pageIndex}/{searchKeyword}","/{id}/{pageIndex}/{searchKeyword}/{searchCondition}", "/{id}/{pageIndex}"})
    public ResponseEntity<MessageVo> selectBoard(
    		@PathVariable("id") String id
    		, @PathVariable("pageIndex") int pageIndex
    		, @PathVariable(name="searchCondition", required=false) String searchCondition
    		, @PathVariable(name="searchKeyword", required=false) String searchKeyword
    		, @RequestParam(name="boardType", required=false) String boardType
    	) {
        List<BoardRVo> list = boardService.selectClientBoard(id, pageIndex, searchKeyword, searchCondition, boardType);
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
    	List<FileVo> selectFileList = fileService.selectFileList(id);
    	PrevNextVo selectPrevNextBoard = boardService.selectPrevNextBoard(id);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.totalCount(1)
            	.data(selectBoardInfo)
            	.files(selectFileList)
            	.prevNextData(selectPrevNextBoard)
            	.build();
        
    	return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    @GetMapping("/WithPinup")
    public ResponseEntity<MessageVo> selectBoardInfoWithPinup() {
    	String pinupId = boardService.selectPinup();
    	BoardRVo selectBoardInfo = boardService.selectBoardInfo(pinupId);
    	
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MessageVo message = MessageVo.builder()
            	.status(StatusEnum.OK)
            	.message("성공 코드")
            	.totalCount(1)
            	.data(selectBoardInfo)
            	.build();
        
    	return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
    
    
}

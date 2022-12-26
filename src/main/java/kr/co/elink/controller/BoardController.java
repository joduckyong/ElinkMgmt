package kr.co.elink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.elink.dto.BoardVo;
import kr.co.elink.service.BoardService;

@RestController
@RequestMapping("/api/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
    @GetMapping("/{id}/{pageIndex}/{searchKeyword}")
    public ResponseEntity<List<BoardVo>> selectBoard(@PathVariable("id") String id, @PathVariable("pageIndex") int pageIndex, @PathVariable("searchKeyword") String searchKeyword) {
        List<BoardVo> list = boardService.selectBoard(id, pageIndex, searchKeyword);
        
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BoardVo> selectBoardInfo(@PathVariable("id") String id) {
    	BoardVo selectBoardInfo = boardService.selectBoardInfo(id);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(selectBoardInfo);
    }
    
    @PostMapping("")
    public ResponseEntity<Integer> insertBoard(@RequestBody BoardVo boardVo) {
    	int insertBoard = boardService.insertBoard(boardVo);
        return ResponseEntity.status(HttpStatus.OK).body(insertBoard);
    }    
    
    @PutMapping("")
    public ResponseEntity<Integer> updateBoard(@RequestBody BoardVo boardVo) {
    	int updateBoard = boardService.updateBoard(boardVo);
        return ResponseEntity.status(HttpStatus.OK).body(updateBoard);
    }    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteBoard(@PathVariable("id") String id) {
    	int deleteBoard = boardService.deleteBoard(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleteBoard);
    }
    
	@DeleteMapping("")
	public ResponseEntity<Integer> deleteBoardIds(@RequestBody List<String> ids) throws Exception{
    	int deleteBoardIds = boardService.deleteBoardIds(ids);
        return ResponseEntity.status(HttpStatus.OK).body(deleteBoardIds);
	}	    
    
}

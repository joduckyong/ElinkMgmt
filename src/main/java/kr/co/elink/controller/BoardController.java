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
	
    @GetMapping("")
    public ResponseEntity<List<BoardVo>> selectBoard(BoardVo boardVo) {
        List<BoardVo> selecctBoard = boardService.selectBoard(boardVo);
        return ResponseEntity.status(HttpStatus.OK).body(selecctBoard);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BoardVo> selectBoardInfo(@PathVariable("id") String id) {
    	BoardVo selectBoardInfo = boardService.selectBoardInfo(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(selectBoardInfo);
    }
    
    @PostMapping("")
    public ResponseEntity<List<BoardVo>> insertBoard(@RequestBody BoardVo boardVo) {
    	List<BoardVo> insertBoard = boardService.insertBoard(boardVo);
        return ResponseEntity.status(HttpStatus.CREATED).body(insertBoard);
    }    
    
    @PutMapping("")
    public ResponseEntity<BoardVo> updateBoard(@RequestBody BoardVo boardVo) {
    	BoardVo updateBoard = boardService.updateBoard(boardVo);
        return ResponseEntity.status(HttpStatus.OK).body(updateBoard);
    }    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBoard(@PathVariable("id") String id) {
    	boardService.deleteBoard(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
    
}

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

import kr.co.elink.dto.ConactUsVo;
import kr.co.elink.service.ConactUsService;

@RestController
@RequestMapping("/api/conactUs")
public class ConactUsController {

	@Autowired
	ConactUsService conactUsService;
	
	@GetMapping("/{id}/{pageIndex}")
    public ResponseEntity<List<ConactUsVo>> selectConactUs(@PathVariable("id") String id, @PathVariable("pageIndex") int pageIndex) {
        List<ConactUsVo> selectConactUs = conactUsService.selectConactUs(id, pageIndex);
        return ResponseEntity.status(HttpStatus.OK).body(selectConactUs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ConactUsVo> selecConactUsInfo(@PathVariable("id") String id) {
    	ConactUsVo selecConactUsInfo = conactUsService.selecConactUsInfo(id);
        return ResponseEntity.status(HttpStatus.OK).body(selecConactUsInfo);
    }
    
    @PostMapping("")
    public ResponseEntity<Integer> insertConactUs(@RequestBody ConactUsVo conactUsVo) {
    	int insertConactUs = conactUsService.insertConactUs(conactUsVo);
        return ResponseEntity.status(HttpStatus.OK).body(insertConactUs);
    }    
    
    @PutMapping("")
    public ResponseEntity<Integer> updateConactUs(@RequestBody ConactUsVo conactUsVo) {
    	int updateConactUs = conactUsService.updateConactUs(conactUsVo);
        return ResponseEntity.status(HttpStatus.OK).body(updateConactUs);
    }    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteConactUs(@PathVariable("id") String id) {
    	int deleteConactUs = conactUsService.deleteConactUs(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleteConactUs);
    }
    
	@DeleteMapping("")
	public ResponseEntity<Integer> deleteConactUsIds(@RequestBody ConactUsVo conactUsVo) throws Exception{
    	int deleteConactUsIds = conactUsService.deleteConactUsIds(conactUsVo);
        return ResponseEntity.status(HttpStatus.OK).body(deleteConactUsIds);
	}
}

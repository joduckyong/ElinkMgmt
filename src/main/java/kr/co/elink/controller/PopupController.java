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

import kr.co.elink.dto.PopupVo;
import kr.co.elink.service.PopupService;

@RestController
@RequestMapping("/api/popup")
public class PopupController {

	@Autowired
	PopupService popupService;
	
    @GetMapping("")
    public ResponseEntity<List<PopupVo>> selectPopup(PopupVo popupVo) {
        List<PopupVo> selectPopup = popupService.selectPopup(popupVo);
        return ResponseEntity.status(HttpStatus.OK).body(selectPopup);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PopupVo> selectPopupInfo(@PathVariable("id") String id) {
    	PopupVo selectPopupInfo = popupService.selectPopupInfo(id);
        return ResponseEntity.status(HttpStatus.OK).body(selectPopupInfo);
    }
    
    @PostMapping("")
    public ResponseEntity<Integer> insertPopup(@RequestBody PopupVo popupVo) {
    	int insertPopup = popupService.insertPopup(popupVo);
        return ResponseEntity.status(HttpStatus.OK).body(insertPopup);
    }    
    
    @PutMapping("")
    public ResponseEntity<Integer> updatePopup(@RequestBody PopupVo popupVo) {
    	int updatePopup = popupService.updatePopup(popupVo);
        return ResponseEntity.status(HttpStatus.OK).body(updatePopup);
    }    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> updatePopup(@PathVariable("id") String id) {
    	int updatePopup = popupService.updatePopup(id);
        return ResponseEntity.status(HttpStatus.OK).body(updatePopup);
    }
    
}

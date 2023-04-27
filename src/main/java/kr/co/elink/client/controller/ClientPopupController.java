package kr.co.elink.client.controller;

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
import org.springframework.web.bind.annotation.RestController;

import kr.co.elink.common.StatusEnum;
import kr.co.elink.dto.FileVo;
import kr.co.elink.dto.MessageVo;
import kr.co.elink.dto.PopupRVo;
import kr.co.elink.service.FileService;
import kr.co.elink.service.PopupService;

@RestController
@RequestMapping("/api/client/popup")
public class ClientPopupController {

	@Autowired
	PopupService popupService;

	@Autowired
	FileService fileService;
	
	@GetMapping({"/{id}/{pageIndex}", "/{id}/{pageIndex}/{searchCondition}"})
	public ResponseEntity<MessageVo> selectPopup(@PathVariable("id") String id,
			@PathVariable("pageIndex") int pageIndex,
			@PathVariable(name = "searchCondition", required = false) String searchCondition) {
		List<PopupRVo> list = popupService.selectPopup(id, pageIndex, searchCondition);
		int totalCount = 0;
		if (list.size() > 0) {
			totalCount = list.get(0).getTotalCount();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		MessageVo message = MessageVo.builder().status(StatusEnum.OK).message("성공 코드").totalCount(totalCount).data(list)
				.build();

		return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MessageVo> selectPopupInfo(@PathVariable("id") String id) {
		PopupRVo selectPopupInfo = popupService.selectPopupInfo(id);
		List<FileVo> selectFileList = fileService.selectFileList(id);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		MessageVo message = MessageVo.builder().status(StatusEnum.OK).message("성공 코드").totalCount(1)
				.data(selectPopupInfo).files(selectFileList).build();

		return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}
	
}

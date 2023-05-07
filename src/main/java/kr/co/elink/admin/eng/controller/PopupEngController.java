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
import kr.co.elink.dto.FileVo;
import kr.co.elink.dto.MessageVo;
import kr.co.elink.dto.PopupRVo;
import kr.co.elink.dto.PopupVo;
import kr.co.elink.eng.service.PopupEngService;
import kr.co.elink.service.FileService;

@RestController
@RequestMapping("/en/api/popup")
public class PopupEngController {

	@Autowired
	PopupEngService popupEngService;

	@Autowired
	FileService fileService;

	@GetMapping({ "/{id}/{pageIndex}", "/{id}/{pageIndex}/{searchCondition}" })
	public ResponseEntity<MessageVo> selectEngPopup(@PathVariable("id") String id,
			@PathVariable("pageIndex") int pageIndex,
			@PathVariable(name = "searchCondition", required = false) String searchCondition) {
		List<PopupRVo> list = popupEngService.selectEngPopup(id, pageIndex, searchCondition);
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
	public ResponseEntity<MessageVo> selectEngPopupInfo(@PathVariable("id") String id) {
		PopupRVo selectPopupInfo = popupEngService.selectEngPopupInfo(id);
		List<FileVo> selectFileList = fileService.selectFileList(id);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		MessageVo message = MessageVo.builder().status(StatusEnum.OK).message("성공 코드").totalCount(1)
				.data(selectPopupInfo).files(selectFileList).build();

		return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<MessageVo> insertEngPopup(@RequestPart PopupVo popupVo,
			@RequestPart(value = "thumbnail", required = false) MultipartFile multipartThumbnail,
			@RequestPart(value = "file", required = false) MultipartFile multipartFile) throws IOException {
		int insertPopup = popupEngService.insertEngPopup(popupVo, multipartThumbnail, multipartFile);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		MessageVo message = MessageVo.builder().status(StatusEnum.OK).message("성공 코드").data(insertPopup).build();

		return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<MessageVo> updateEngPopup(@RequestPart PopupVo popupVo,
			@RequestPart(value = "thumbnail", required = false) MultipartFile multipartThumbnail,
			@RequestPart(value = "file", required = false) MultipartFile multipartFile) throws IOException {

		int updatePopup = popupEngService.updateEngPopup(popupVo, multipartThumbnail, multipartFile);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		MessageVo message = MessageVo.builder().status(StatusEnum.OK).message("성공 코드").data(updatePopup).build();

		return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MessageVo> deleteEngPopup(@PathVariable("id") String id) {
		int deletePopup = popupEngService.deleteEngPopup(id);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		MessageVo message = MessageVo.builder().status(StatusEnum.OK).message("성공 코드").data(deletePopup).build();

		return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}

	@DeleteMapping("")
	public ResponseEntity<MessageVo> deleteEngPopupIds(@RequestBody PopupVo popupVo) throws Exception {
		int deletePopupIds = popupEngService.deleteEngPopupIds(popupVo);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		MessageVo message = MessageVo.builder().status(StatusEnum.OK).message("성공 코드").data(deletePopupIds).build();

		return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}
}

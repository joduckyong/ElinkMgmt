package kr.co.elink.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.elink.dto.FileVo;
import kr.co.elink.service.FileService;

@RestController
@RequestMapping("/api/file")
public class FileController {
	
	@Autowired
	FileService fileService;
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws UnsupportedEncodingException{
		
		FileVo fileVo = fileService.selectFileInfo(fileName);
		Resource resource = null;
		try {
			resource = fileService.loadFile(fileVo);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

       String contentType = null;
       try {
           contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
       } catch (IOException e) {
           e.printStackTrace();
       }

       if(contentType == null) {
           contentType = "application/octet-stream";
       }

       return ResponseEntity.ok()
               .contentType(MediaType.parseMediaType(contentType))
               .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(fileVo.getFileOriginNm(),"UTF-8") + "\"")
               .body(resource);
   }
	
	@GetMapping("/zip/{fileName}")
	public ResponseEntity<Resource> downloadFile2(@PathVariable String fileName, HttpServletRequest request) throws UnsupportedEncodingException{
		
		FileVo fileVo = fileService.selectFileInfo(fileName);
		Resource resource = null;
		try {
			resource = fileService.loadFile2(fileVo);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(contentType == null) {
			contentType = "application/zip";
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.COOKIE, "fileDownload=true; path=/")
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(fileVo.getFileOriginNm(),"UTF-8") + "\"")
				.body(resource);
	}
	
	@GetMapping("/image/{fileName}")
	public ResponseEntity<Resource> viewImage(@PathVariable String fileName, HttpServletRequest request){
		
		FileVo fileVo = null;
		if(fileName.indexOf("s_") > -1) {	//썸네일 이미지
			fileVo = fileService.selectFileInfo(fileName);
		}else {		//원본 이미지
			fileVo = fileService.selectFileInfo("s_" + fileName);
			fileVo.setFileNm(fileName);
		}
		Resource resource = null;
		try {
			resource = fileService.loadFile(fileVo);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String contentType = null;
       try {
           contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
       } catch (IOException e) {
           e.printStackTrace();
       }
       
       return ResponseEntity.ok()
               .contentType(MediaType.parseMediaType(contentType))
               .body(resource);
	}

    @PostMapping("/")
    public int insertTempFile(
    		@RequestPart(value="file", required = false) MultipartFile multipartFile) throws IOException {
    	int insertTempFile = fileService.insertTempFile(multipartFile);
        return insertTempFile;
    }
    
	@GetMapping("/img/{fileNo}")
	public ResponseEntity<Resource> viewTempImage(@PathVariable int fileNo, HttpServletRequest request){
		
		FileVo fileVo = fileService.selectTempFileInfo(fileNo);
		Resource resource = null;
		
		try {
			resource = fileService.loadTempFile(fileVo);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String contentType = null;
       try {
           contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
       } catch (IOException e) {
           e.printStackTrace();
       }
       
       return ResponseEntity.ok()
               .contentType(MediaType.parseMediaType(contentType))
               .body(resource);
	}
	
}

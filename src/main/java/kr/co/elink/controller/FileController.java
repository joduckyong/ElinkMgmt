package kr.co.elink.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.elink.dto.FileVo;
import kr.co.elink.service.FileService;

@RestController
@RequestMapping("/api/file")
public class FileController {
	
	@Value("${server.file.path}")
	private String serverFilePath;
	
	@Value("${server.thumbnail.path}")
	private String serverThumbnailPath;
	
	@Autowired
	FileService fileService;
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
		
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
               .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileVo.getFileOriginNm() + "\"")
               .body(resource);
   }
	
	@GetMapping("/image/{fileName}")
	public ResponseEntity<Resource> viewImage(@PathVariable String fileName, HttpServletRequest request){
		
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
       
       return ResponseEntity.ok()
               .contentType(MediaType.parseMediaType(contentType))
//               .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileVo.getFileOriginNm() + "\"")
               .body(resource);
		
	}

}

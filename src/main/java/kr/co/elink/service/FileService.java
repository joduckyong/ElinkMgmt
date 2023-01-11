package kr.co.elink.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.elink.common.util.UploadFile;
import kr.co.elink.dto.FileVo;
import kr.co.elink.mapper.FileMapper;

@Service
public class FileService {
	
	@Value("${server.file.path}")
	private String serverFilePath;
	
	@Value("${server.thumbnail.path}")
	private String serverThumbnailPath;
	
	@Autowired
	FileMapper fileMapper;
	
	@Autowired
	UploadFile uploadFile;
	
	public FileVo selectFileInfo(String fileNm) {
		return fileMapper.selectFileInfo(fileNm);
	}
	
	public List<FileVo> selectFileList(String fileId) {
		return fileMapper.selectFileList(fileId);
	}
	
	public Resource loadFile(FileVo fileVo) throws FileNotFoundException {
		
		String serverPath = "";
		if(fileVo.getFileType().equals("1")) {	//썸네일
			serverPath = serverThumbnailPath;
		}else {
			serverPath = serverFilePath;
		}

        try {
            Path file = Paths.get(serverPath + fileVo.getFilePath())
                    .toAbsolutePath().normalize().resolve(fileVo.getFileNm()).normalize();
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else {
                throw new FileNotFoundException("Could not find file");
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not download file");
        }

    }

	@Transactional
	public int insertTempFile(MultipartFile multipartFile) throws IOException{
		
		int result = 0;
    	
    	if(multipartFile != null) {
    		FileVo fileVo = new FileVo();
    		result = fileMapper.insertTempFile(uploadFile.upload2(multipartFile, fileVo));
    	}
		
		return result;
	};
}

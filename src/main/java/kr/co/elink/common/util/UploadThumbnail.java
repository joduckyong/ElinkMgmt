package kr.co.elink.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.elink.dto.BoardVo;
import kr.co.elink.dto.FileVo;
import net.coobird.thumbnailator.Thumbnails;

@Component
public class UploadThumbnail {
	
	@Value("${server.thumbnail.path}")
	private String serverThumbnailPath;
	
	public String makeDir() {
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator +cal.get(Calendar.YEAR) + "";
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		if(!new File(serverThumbnailPath+datePath, datePath).exists()) {
			new File(serverThumbnailPath, yearPath).mkdir();
			new File(serverThumbnailPath, monthPath).mkdir();
			new File(serverThumbnailPath, datePath).mkdir();
		}
		
		return datePath;
		
	}
	
	public FileVo upload(MultipartFile multipartFile, FileVo vo) throws IOException {
		UUID uuid = UUID.randomUUID();
		String originalFileName = multipartFile.getOriginalFilename();
		String fileExt = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
		String fileName = "s_" + uuid + "." + fileExt;
		String dir = makeDir();
		
		File file = new File(serverThumbnailPath + dir, fileName);
		File thumbnailFile = new File(serverThumbnailPath + dir, fileName);
		
	    multipartFile.transferTo(file);
		
		BufferedImage bi = ImageIO.read(file);
		//비율 
		double ratio = 3;
		//넓이 높이
		int width = (int) (bi.getWidth() / ratio);
		int height = (int) (bi.getHeight() / ratio);					
	
		Thumbnails.of(file)
	    .size(width, height)
	    .toFile(thumbnailFile);
		
		FileVo fileVo = new FileVo();
		fileVo.setFileId(vo.getFileId());
		fileVo.setFileNm(fileName);
		fileVo.setFileOriginNm(originalFileName);
		fileVo.setFilePath(dir + File.separator + fileName);
		fileVo.setFileSize(thumbnailFile.length());
		fileVo.setFileType("1");
		
		return fileVo;
		
	}
	
	
	
}

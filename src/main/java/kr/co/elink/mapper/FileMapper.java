package kr.co.elink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.elink.dto.FileVo;

@Repository
@Mapper
public interface FileMapper {
	
	public FileVo selectFileInfo(String fileNm);
	
	public FileVo selectTempFileInfo(int fileNo);
	
	public List<FileVo> selectFileList(String fileId);
	
	public int insertFile(FileVo fileVo);
	
	public int insertTempFile(FileVo fileVo);
	
	public int deleteFile(String id);
	
	public int deleteFileIds(FileVo fileVo);
	
	public int deleteFileForName(FileVo fileVo);
	
}

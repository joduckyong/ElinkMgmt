package kr.co.elink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.elink.dto.FileVo;

@Repository
@Mapper
public interface FileMapper {
	
	public List<FileVo> selectFile(FileVo fileVo);
	
	public FileVo selectFileInfo(String fileNm);
	
	public int insertFile(FileVo fileVo);
	
	public int deleteFile(String id);
	
}

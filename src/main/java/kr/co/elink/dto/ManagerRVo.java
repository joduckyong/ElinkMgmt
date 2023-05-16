package kr.co.elink.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ManagerRVo
{
	/** 관리자 번호 */
	private int adminNo;
	
	/** 관리자 ID */
    private String adminId;
    
    /** 관리자 이름 */
    private String adminNm;
    
    /** 권한 (관리자:ADMIN) */
    private String adminRole;
    
    /** 메인페이지 관리 */
    private String roleMain;
    
    /** 홍보센터 관리 */
    private String roleProm;
    
    /** Contact Us 관리 */
    private String roleCont;
    
    /** 관리자권한 관리 */
    private String roleMgm;
    
    /** 투자정보 관리 */
    private String roleInve;
    
    /** 채용정보 관리 */
    private String roleJobs;
    
    /** EV충전소 관리 */
    private String roleEvst;
    
    /** 사용유무(Y:사용, N:미사용) */
    private String useYn;
    
    @JsonIgnore
	private int totalCount;
    
	private int rnum;
    
}

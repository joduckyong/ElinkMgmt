package kr.co.elink.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenVo {
//	private String grantType;
	private String accessToken;
	private String refreshToken;
	private String tAdminId;
	private String tAdminNm;
}

package kr.co.elink.dto;

import kr.co.elink.common.StatusEnum;
import lombok.Data;

@Data
public class MessageVo {
	private StatusEnum status;
    private String message;
    private Object data;

    public MessageVo() {
        this.status = StatusEnum.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }

}

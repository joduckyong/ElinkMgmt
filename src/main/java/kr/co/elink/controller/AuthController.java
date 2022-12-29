package kr.co.elink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.elink.dto.AdminVo;
import kr.co.elink.dto.TokenVo;
import kr.co.elink.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthService authService;
    
    @PostMapping("")
    public TokenVo login(@RequestBody AdminVo adminVo) {
        String memberId = adminVo.getAdminId();
        String password = adminVo.getPassword();
        TokenVo tokenVo = authService.login(memberId, password);
        
        return tokenVo;
    }    
    
    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
package kr.co.elink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.elink.dto.AdminVo;
import kr.co.elink.jwt.JwtTokenProvider;
import kr.co.elink.mapper.AuthMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	AuthMapper authMapper;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		AdminVo adminVo = authMapper.selectAdmin(loginId);
		return createUserDetails(loginId, adminVo);
	}

	// 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
	private UserDetails createUserDetails(String loginId, AdminVo adminVo) {

		if (adminVo == null) {
			throw new BadCredentialsException(loginId + " -> 사용자가 없습니다.");
		}

//		log.info("adminVo.getUsername() : " + adminVo.getUsername());
//		log.info("passwordEncoder.encode(adminVo.getPassword()) : " + passwordEncoder.encode(adminVo.getPassword()));
//        log.info("getRoles : "+adminVo.getAdminRole());
		return User.builder().username(adminVo.getUsername()).password(adminVo.getPassword())
				.password(passwordEncoder.encode(adminVo.getPassword()))
                .roles(adminVo.getAdminRole()).build();
	}
}
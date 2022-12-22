package com.ezen.myProject.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezen.myProject.domain.UserVO;
import com.ezen.myProject.repository.UserDAO;

import lombok.extern.slf4j.Slf4j;

@Service // 구현하는 객체에게만 어노테이션을 붙임
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Inject
	private UserDAO userDao;
	@Inject
	BCryptPasswordEncoder passwordEnconder; // 비밀번호 암호화
	
	@Override
	public boolean signUp(UserVO user) {
		logger.info(">>> signup check2");
		//아이디가 중복되면 회원가입 실패
		//아이디와 일치하는 정보를 db에서 가져옴
		UserVO tmpUser = userDao.getUser(user.getId());
		//tmpUser가 null이 아니라면 이미 가입된 회원 => 아이디 중복 => 회원가입 실패
		if(tmpUser != null) {
			return false;
		}
		
		//아이디가 중복이 아닌경우 회원가입 가능
		//아이디가 없거나, 유효성 검사(아이디 규칙 등)에 맞지 않는다면 실패
		//아이디 유효성 검사 : 아이디가 입력이 되었는지만 체크
		if(user.getId()==null || user.getId().length() == 0) {
			return false;
		}
		// 비밀번호 유효성 검사
		if(user.getPw()==null || user.getPw().length() == 0) {
			return false;
		}
		
		//회원가입
		//비밀번호 암호화 과정
		String pw = user.getPw();
		//encode(암호화) / matches(원래 비번, 암호화된 비번)
		String encodePw = passwordEnconder.encode(pw); //pw 암호화된 패스워드
		user.setPw(encodePw); // 암호화된 비밀번호를 실제 user의 비밀번호로 set
		
		//회원가입 => insert
		userDao.insertUser(user);
		
		return true;
	}

	@Override
	public UserVO isUser(String id, String pw) {
		UserVO user = userDao.getUser(id); // 앞에서 했던 메서드 호출
		//가져온 User 객체의 비밀번호와 입력받은 비밀번호가 같은 지 확인
		
		//유저가 없을 경우
		if(user == null) {
			return null;
		}
		//matches(원래 비번, 암호화된 비번)
		if(passwordEnconder.matches(pw, user.getPw())) { // 입력받은 비번과, 암호화된 비번을 가지고온다
			return user;
		}else {
			return null;
		}
	}

	@Override
	public boolean updateUser(UserVO user) {
		UserVO tmpUser = userDao.getUser(user.getId());
		if(tmpUser != null) {
			return false;
		}
		if(user.getId()==null || user.getId().length() == 0) {
			return false;
		}
		
		int isOK = userDao.updateUser(user);
		logger.info(">>>update check" + isOK);
		return true;
	}
}

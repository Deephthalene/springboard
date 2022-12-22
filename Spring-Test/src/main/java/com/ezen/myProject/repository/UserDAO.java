package com.ezen.myProject.repository;

import com.ezen.myProject.domain.UserVO;

public interface UserDAO {

	UserVO getUser(String id);

	void insertUser(UserVO user);

	int updateUser(UserVO user);

}

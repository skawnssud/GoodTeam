package com.my.spring.mapper;

import java.util.List;

import com.my.spring.domain.UserVO;

public interface UserMapper {
	public List<UserVO> viewAll();
	public UserVO searchByAccount(String account);
	public Boolean createAccount(UserVO newUser);
}

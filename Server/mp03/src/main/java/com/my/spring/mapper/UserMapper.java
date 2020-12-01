package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.UserVO;

public interface UserMapper {
	public List<UserVO> viewAll();
	public UserVO searchByAccount(String account);
	public Boolean createAccount(@Param("newUser") UserVO newUser);
}

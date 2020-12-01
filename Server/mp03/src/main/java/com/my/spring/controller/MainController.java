package com.my.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.UserVO;
import com.my.spring.service.UserService;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/user")
public class MainController {
	
	@Autowired
	private UserService userService;
	
	// 전체 조회
	@RequestMapping("/all")
	public List<UserVO> viewAll() {
		return userService.viewAll();
	}
	
	// 계정 조회
	@RequestMapping(value = "/account/{account}", method = RequestMethod.GET)
	public UserVO searchByAccount(@PathVariable String account) {
		return userService.searchByAccount(account);
	}
	
	// 계정 생성
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Boolean createAccount(@ModelAttribute UserVO newUser) {
		return userService.createAccount(newUser);
	}
	
	// ID <-> PW 매칭 여부: True -> 일치 False -> 불일치
	@RequestMapping(value = "/login/{account}/{pw}", method = RequestMethod.GET)
	public Boolean checkValidation(@PathVariable String account, @PathVariable String pw) {
		if (userService.searchByAccount(account) != null) {
			return userService.searchByAccount(account).getPw().equals(pw);
		} else {
			return false;
		}
	}
}

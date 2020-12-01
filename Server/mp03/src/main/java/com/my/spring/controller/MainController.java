package com.my.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.UserVO;
import com.my.spring.domain.WorkerViewVO;
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
		if (userService.searchByAccount(account) != null) {
			return userService.searchByAccount(account);
		} else {
			UserVO allowed = new UserVO();
			allowed.setAccount("allowed");
			return allowed;
		}
	}
	
	// 계정 생성
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Boolean createAccount(@RequestBody UserVO newUser) {

		System.out.println(newUser.getId());
		System.out.println(newUser.getRole());
		System.out.println(newUser.getAccount());
		System.out.println(newUser.getNick());
		System.out.println(newUser.getPw());
		System.out.println(newUser.getName());
		System.out.println(newUser.getAge());
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
	
	// WorkerView 오브젝트 반환
	@RequestMapping(value="/workerView/{id_branch}", method = RequestMethod.GET)
	public List<WorkerViewVO> getWorkerViewByIdBranch(@PathVariable int id_branch) {
		return userService.getWorkerViewByIdBranch(id_branch);
	}
}

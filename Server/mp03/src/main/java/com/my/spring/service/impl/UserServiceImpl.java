package com.my.spring.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.RelationVO;
import com.my.spring.domain.UserVO;
import com.my.spring.domain.WorkerViewVO;
import com.my.spring.mapper.UserMapper;
import com.my.spring.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;
	
	@Override
	public List<UserVO> viewAll() {
		return mapper.viewAll();
	}

	@Override
	public UserVO searchByAccount(String account) {
		return mapper.searchByAccount(account);
	}

	@Override
	public Boolean createAccount(UserVO newUser) {
		return mapper.createAccount(newUser);
	}

	@Override
	public List<WorkerViewVO> getWorkerViewByIdBranch(int id_worker) {
		return mapper.getWorkerViewByIdBranch(id_worker);
	}

	@Override
	public Boolean createRelation(RelationVO newRelation) {
		return mapper.createRelation(newRelation);
	}

	@Override
	public String getAccountById(int id_worker) {
		// TODO Auto-generated method stub
		return mapper.getAccountById(id_worker);
	}

}

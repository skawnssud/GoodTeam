package com.my.spring.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.RelationVO;
import com.my.spring.domain.UserVO;
import com.my.spring.domain.WorkerViewVO;

public interface UserService {
	public List<UserVO> viewAll();
	public UserVO searchByAccount(String account);
	public Boolean createAccount(UserVO newUser);
	public List<WorkerViewVO> getWorkerViewByIdBranch(int id_branch);
	public Boolean createRelation(RelationVO newRelation);
	public String getAccountById(int id_worker);
}

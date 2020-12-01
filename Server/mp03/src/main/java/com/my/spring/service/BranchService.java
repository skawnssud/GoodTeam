package com.my.spring.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.BranchVO;

public interface BranchService {
	public List<BranchVO> viewAll();
	public BranchVO searchByTitle(String title);
	public Boolean createBranch(@Param("newBranch") BranchVO newBranch);
	public List<BranchVO> searchByIdBoss(int id_boss);
	public Boolean deleteBranch(@Param("target") BranchVO target);
	public Boolean modifyBranch(@Param("newBranch") BranchVO newBranch);
}

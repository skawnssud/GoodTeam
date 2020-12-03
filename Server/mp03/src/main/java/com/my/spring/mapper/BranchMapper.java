package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.BranchVO;

public interface BranchMapper {
	public List<BranchVO> viewAll();
	public BranchVO searchByTitle(String title);
	public Boolean createBranch(@Param("newBranch") BranchVO newBranch);
	public List<BranchVO> searchByIdBoss(int id_boss);
	public Boolean deleteBranch(int id_branch);
	public Boolean modifyBranch(@Param("newBranch") BranchVO newBranch);
	public int getIdBranch(@Param("target") BranchVO target);
}

package com.my.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.BranchVO;
import com.my.spring.mapper.BranchMapper;
import com.my.spring.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService {
	
	@Autowired
	private BranchMapper mapper;

	@Override
	public List<BranchVO> viewAll() {
		return mapper.viewAll();
	}

	@Override
	public BranchVO searchByTitle(String title) {
		return mapper.searchByTitle(title);
	}

	@Override
	public Boolean createBranch(BranchVO newBranch) {
		return mapper.createBranch(newBranch);
	}

	@Override
	public List<BranchVO> searchByIdBoss(int id_boss) {
		return mapper.searchByIdBoss(id_boss);
	}

	@Override
	public Boolean deleteBranch(int id_branch) {
		return mapper.deleteBranch(id_branch);
	}

	@Override
	public Boolean modifyBranch(BranchVO newBranch) {
		return mapper.modifyBranch(newBranch);
	}

	@Override
	public int getIdBranch(String title, int id_boss) {
		return mapper.getIdBranch(title, id_boss);
	}

	@Override
	public BranchVO getBranchByIdBranch(int id_branch) {
		// TODO Auto-generated method stub
		return mapper.getBranchByIdBranch(id_branch);
	}

}

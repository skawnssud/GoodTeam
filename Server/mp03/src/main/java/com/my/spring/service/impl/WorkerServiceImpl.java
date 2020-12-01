package com.my.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.my.spring.domain.WorkerVO;
import com.my.spring.mapper.WorkerMapper;
import com.my.spring.service.WorkerService;

public class WorkerServiceImpl implements WorkerService {
	@Autowired
	private WorkerMapper mapper;

	@Override
	public List<WorkerVO> viewAll() {
		return mapper.viewAll();
	}

	@Override
	public List<WorkerVO> searchByIdWorker(int id_worker) {
		return mapper.searchByIdWorker(id_worker);
	}

	@Override
	public List<WorkerVO> searchByIdBranch(int id_branch) {
		return mapper.searchByIdBranch(id_branch);
	}

	@Override
	public Boolean createWorker(WorkerVO newWorker) {
		return mapper.createWorker(newWorker);
	}

	@Override
	public Boolean deleteWorker(WorkerVO target) {
		return mapper.deleteWorker(target);
	}

	@Override
	public Boolean modifyWorker(WorkerVO newWorker) {
		return mapper.modifyWorker(newWorker);
	}

}

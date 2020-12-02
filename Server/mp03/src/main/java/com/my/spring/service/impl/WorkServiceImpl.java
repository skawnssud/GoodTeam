package com.my.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.WorkerInfoVO;
import com.my.spring.mapper.WorkMapper;
import com.my.spring.service.WorkService;

@Service
public class WorkServiceImpl implements WorkService {
	
	@Autowired
	private WorkMapper mapper;

	@Override
	public List<WorkerInfoVO> searchWorkerInfoByIdWorker(int id_worker, int id_relation) {
		return mapper.searchWorkerInfoByIdWorker(id_worker, id_relation);
	}

	@Override
	public Boolean createWorkerInfo(WorkerInfoVO newWorkerInfo, int id_branch) {
		return mapper.createWorkerInfo(newWorkerInfo, id_branch);
	}

	@Override
	public Boolean deleteWorkerInfo(WorkerInfoVO target) {
		return mapper.deleteWorkerInfo(target);
	}

	@Override
	public Boolean modifyWorkerInfo(WorkerInfoVO newWorkerInfo) {
		return mapper.modifyWorkerInfo(newWorkerInfo);
	}
	

}

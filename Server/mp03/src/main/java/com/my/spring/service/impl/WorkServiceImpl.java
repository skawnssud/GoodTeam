package com.my.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.WorkVO;
import com.my.spring.domain.WorkerDetailVO;
import com.my.spring.domain.WorkerInfoVO;
import com.my.spring.mapper.WorkMapper;
import com.my.spring.service.WorkService;

@Service
public class WorkServiceImpl implements WorkService {
	
	@Autowired
	private WorkMapper mapper;

	@Override
	public WorkerInfoVO searchWorkerInfoByIdWorker(int id_worker, int id_branch) {
		return mapper.searchWorkerInfoByIdWorker(id_worker, id_branch);
	}

	@Override
	public Boolean createWorkerInfo(WorkerInfoVO newWorkerInfo, int id_branch) {
		return mapper.createWorkerInfo(newWorkerInfo, id_branch);
	}

	@Override
	public Boolean deleteWorkerInfo(int id_worker, int id_branch) {
		return mapper.deleteWorkerInfo(id_worker, id_branch);
	}

	@Override
	public Boolean modifyWorkerInfo(WorkerInfoVO newWorkerInfo) {
		return mapper.modifyWorkerInfo(newWorkerInfo);
	}

	@Override
	public List<WorkVO> getWorkByIdWorkerInfo(int id_workerinfo) {
		return mapper.getWorkByIdWorkerInfo(id_workerinfo);
	}

	@Override
	public Boolean createWorkByIdWorkerInfo(WorkVO work) {
		return mapper.createWorkByIdWorkerInfo(work);
	}

	@Override
	public Boolean modifyWork(WorkVO work) {
		return mapper.modifyWork(work);
	}

	@Override
	public Boolean deleteWorksByIdWorkerInfo(int id_workerinfo) {
		return mapper.deleteWorksByIdWorkerInfo(id_workerinfo);
	}

	@Override
	public List<WorkerInfoVO> getWorkersByIdBranch(int id_branch) {
		return mapper.getWorkersByIdBranch(id_branch);
	}

	@Override
	public Boolean createWorkerDetail(WorkerDetailVO item) {
		// TODO Auto-generated method stub
		return mapper.createWorkerDetail(item);
	}

	@Override
	public Boolean modifyWorkerDetail(WorkerDetailVO item) {
		// TODO Auto-generated method stub
		return mapper.modifyWorkerDetail(item);
	}

	@Override
	public WorkerDetailVO getWorkerDetailByIdWorkerInfo(int id_workerInfo) {
		// TODO Auto-generated method stub
		return mapper.getWorkerDetailByIdWorkerInfo(id_workerInfo);
	}
	
	

}

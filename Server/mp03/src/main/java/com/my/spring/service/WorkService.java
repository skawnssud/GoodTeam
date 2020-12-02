package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.WorkerInfoVO;

public interface WorkService {
	public List<WorkerInfoVO> searchWorkerInfoByIdWorker(int id_worker, int id_relation);
	public Boolean createWorkerInfo(WorkerInfoVO newWorkerInfo, int id_branch);
	public Boolean deleteWorkerInfo(WorkerInfoVO target);
	public Boolean modifyWorkerInfo(WorkerInfoVO newWorkerInfo);

}

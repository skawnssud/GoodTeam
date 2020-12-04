package com.my.spring.service;

import java.util.List;
import com.my.spring.domain.WorkVO;
import com.my.spring.domain.WorkerDetailVO;
import com.my.spring.domain.WorkerInfoVO;

public interface WorkService {
	public WorkerInfoVO searchWorkerInfoByIdWorker(int id_worker, int id_branch);
	public Boolean createWorkerInfo(WorkerInfoVO newWorkerInfo, int id_branch);
	public Boolean deleteWorkerInfo(int id_worker, int id_branch);
	public Boolean modifyWorkerInfo(WorkerInfoVO newWorkerInfo);
	public List<WorkVO> getWorkByIdWorkerInfo(int id_workerinfo);
	public Boolean createWorkByIdWorkerInfo(WorkVO work);
	public Boolean modifyWork(WorkVO work);
	public Boolean deleteWorksByIdWorkerInfo(int id_workerinfo);
	public List<WorkerInfoVO> getWorkersByIdBranch(int id_branch);
	public Boolean createWorkerDetail(WorkerDetailVO item);
	public Boolean modifyWorkerDetail(WorkerDetailVO item);
	public WorkerDetailVO getWorkerDetailByIdWorkerInfo(int id_workerInfo);
	public List<WorkerInfoVO> getWorkerInfosByIdWorker(int id_worker);
}

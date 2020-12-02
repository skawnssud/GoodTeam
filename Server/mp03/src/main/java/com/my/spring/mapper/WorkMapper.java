package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.WorkVO;
import com.my.spring.domain.WorkerInfoVO;

public interface WorkMapper {
	public WorkerInfoVO searchWorkerInfoByIdWorker(@Param("id_worker") int id_worker,@Param("id_branch") int id_branch);
	public Boolean createWorkerInfo(@Param("newWorkerInfo") WorkerInfoVO newWorkerInfo, @Param("id_branch") int id_branch);
	public Boolean deleteWorkerInfo(@Param("id_worker") int id_worker, @Param("id_branch") int id_branch);
	public Boolean modifyWorkerInfo(@Param("newWorkerInfo") WorkerInfoVO newWorkerInfo);
	public List<WorkVO> getWorkByIdWorkerInfo(@Param("id_workerinfo") int id_workerinfo);
	public Boolean createWorkByIdWorkerInfo(@Param("work") WorkVO work);
	public Boolean modifyWork(@Param("work") WorkVO work);
	public Boolean deleteWorksByIdWorkerInfo(@Param("id_workerinfo") int id_workerinfo);
	public List<WorkerInfoVO> getWorkersByIdBranch(@Param("id_branch") int id_branch);
}

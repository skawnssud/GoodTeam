package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.WorkerInfoVO;

public interface WorkMapper {
	public List<WorkerInfoVO> searchWorkerInfoByIdWorker(@Param("id_worker") int id_worker,@Param("id_relation") int id_relation);
	public Boolean createWorkerInfo(@Param("newWorkerInfo") WorkerInfoVO newWorkerInfo, @Param("id_branch") int id_branch);
	public Boolean deleteWorkerInfo(@Param("id_worker") int id_worker, @Param("id_branch") int id_branch);
	public Boolean modifyWorkerInfo(@Param("newWorkerInfo") WorkerInfoVO newWorkerInfo);

}

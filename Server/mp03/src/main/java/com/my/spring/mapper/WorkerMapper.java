package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.WorkerVO;

public interface WorkerMapper {
	public List<WorkerVO> viewAll();
	public List<WorkerVO> searchByIdWorker(int id_worker);
	public List<WorkerVO> searchByIdBranch(int id_branch);
	public Boolean createWorker(@Param("newWorker") WorkerVO newWorker);
	public Boolean deleteWorker(@Param("target") WorkerVO target);
	public Boolean modifyWorker(@Param("newWorker") WorkerVO newWorker);

}

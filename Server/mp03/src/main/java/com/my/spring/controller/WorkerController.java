package com.my.spring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.WorkerVO;
import com.my.spring.service.WorkerService;

@RestController
@RequestMapping(value = "/worker")
public class WorkerController {
	
	private WorkerService service;
	
	@RequestMapping(value = "/id_branch/{id_branch}", method = RequestMethod.GET)
	public List<WorkerVO> searchRelationByIdBranch(@PathVariable int id_branch) {
		return service.searchByIdBranch(id_branch);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Boolean createRelation(@RequestBody WorkerVO newWorker) {
		return service.createWorker(newWorker);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Boolean modifyRelation(@RequestBody WorkerVO newWorker) {
		return service.modifyWorker(newWorker);
	}
	
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public Boolean deleteRelation(@RequestBody WorkerVO target) {
		return service.deleteWorker(target);
	}

}

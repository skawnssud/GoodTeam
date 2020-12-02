package com.my.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.WorkerInfoVO;
import com.my.spring.service.WorkService;

@RestController
@RequestMapping(value = "/work")
public class WorkController {
	
	@Autowired
	private WorkService service;
	
	@RequestMapping(value = "/{id_worker}/{id_relation}", method = RequestMethod.GET)
	public List<WorkerInfoVO> searchWorkerInfoByIdWorker(@PathVariable int id_worker, @PathVariable int id_relation) {
		return service.searchWorkerInfoByIdWorker(id_worker, id_relation);
	}
	
	@RequestMapping(value = "/{id_branch}", method = RequestMethod.POST)
	public Boolean createWorkerInfo(@RequestBody WorkerInfoVO newWorkerInfo, @PathVariable int id_branch) {

		System.out.println("::createWorkerInfo::\n");
		System.out.println("id\t| " + newWorkerInfo.getId());
		System.out.println("id_worker\t| " + newWorkerInfo.getId_worker());
		System.out.println("TimeStart\t| " + newWorkerInfo.getTimeStart());
		System.out.println("TimeEnd\t| " + newWorkerInfo.getTimeEnd());
		System.out.println("Payment\t| " + newWorkerInfo.getPayment());
		return service.createWorkerInfo(newWorkerInfo, id_branch);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Boolean modifyWorkerInfo(@RequestBody WorkerInfoVO newWorkerInfo) {
		return service.modifyWorkerInfo(newWorkerInfo);
	}
	
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public Boolean deleteWorkerInfo(@RequestBody WorkerInfoVO target) {
		return service.deleteWorkerInfo(target);
	}

}

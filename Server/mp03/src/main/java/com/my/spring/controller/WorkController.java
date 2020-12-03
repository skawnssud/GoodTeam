package com.my.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.WorkVO;
import com.my.spring.domain.WorkerInfoVO;
import com.my.spring.service.WorkService;

@RestController
@RequestMapping(value = "/work")
public class WorkController {
	
	@Autowired
	private WorkService service;
	
	@RequestMapping(value = "/{id_worker}/{id_branch}", method = RequestMethod.GET)
	public WorkerInfoVO searchWorkerInfoByIdWorker(@PathVariable int id_worker, @PathVariable int id_branch) {
		System.out.println("::searchWorkerInfoByIdWorker");
		System.out.println();
		System.out.println("id_worker\t|\t" + id_worker);
		System.out.println("id_branch\t|\t" + id_branch);
		System.out.println();
		return service.searchWorkerInfoByIdWorker(id_worker, id_branch);
	}
	
	@RequestMapping(value = "/{id_branch}", method = RequestMethod.POST)
	public Boolean createWorkerInfo(@RequestBody WorkerInfoVO newWorkerInfo, @PathVariable int id_branch) {
		System.out.println("::createWorkerInfo::\n");
		System.out.println("id\t| " + newWorkerInfo.getId());
		System.out.println("id_worker\t| " + newWorkerInfo.getId_worker());
		System.out.println("TimeStart\t| " + newWorkerInfo.getTimeStart());
		System.out.println("TimeEnd\t\t| " + newWorkerInfo.getTimeEnd());
		System.out.println("Payment\t\t| " + newWorkerInfo.getPayment());
		return service.createWorkerInfo(newWorkerInfo, id_branch);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Boolean modifyWorkerInfo(@RequestBody WorkerInfoVO newWorkerInfo) {
		System.out.println("::modifyWorkerInfo::");
		System.out.println("id\t| " + newWorkerInfo.getId());
		System.out.println("id_worker\t| " + newWorkerInfo.getId_worker());
		System.out.println("TimeStart\t| " + newWorkerInfo.getTimeStart());
		System.out.println("TimeEnd\t\t| " + newWorkerInfo.getTimeEnd());
		System.out.println("Payment\t\t| " + newWorkerInfo.getPayment());
		return service.modifyWorkerInfo(newWorkerInfo);
	}
	
	@RequestMapping(value = "/delete/{id_worker}/{id_branch}", method = RequestMethod.DELETE)
	public Boolean deleteWorkerInfo(@PathVariable int id_worker, @PathVariable int id_branch) {
		return service.deleteWorkerInfo(id_worker, id_branch);
	}
	
	@RequestMapping(value = "/work/{id_workerinfo}", method = RequestMethod.GET)
	public List<WorkVO> getWorkByIdWorkerInfo(@PathVariable int id_workerinfo) {
		System.out.println("::getWorkByIdWorkerInfo::");
		System.out.println();System.out.println("id_workerinfo\t|\t" + id_workerinfo);
		return service.getWorkByIdWorkerInfo(id_workerinfo);
	}
	
	@RequestMapping(value = "/work", method = RequestMethod.POST)
	public Boolean createWorkByIdWorkerInfo(@RequestBody WorkVO work) {
		System.out.println("::createWorkByIdWorkerInfo::");
		System.out.println();
		System.out.println("dateWork\t|\t" + work.getDateWork());
		System.out.println("HoursOfWork\t|\t" + work.getHoursOfWork());
		System.out.println("payment\t|\t" + work.getPayment());
		System.out.println("timeStart\t|\t" + work.getTimeStart());
		System.out.println("timeEnd\t|\t" + work.getTimeEnd());
		System.out.println("id_workerInfo\t| " + work.getId_workerInfo());
		System.out.println("id\t\t\t| " + work.getId());
		System.out.println();
		return service.createWorkByIdWorkerInfo(work);
	}
	
	@RequestMapping(value = "/work", method = RequestMethod.PUT)
	public Boolean modifyWork(@RequestBody WorkVO work) {
		System.out.println("::modifyWork::");
		System.out.println("payment\t|\t" + work.getPayment());
		System.out.println("id_workerInfo\t|\t" + work.getId_workerInfo());
		System.out.println("TimeStart\t|\t" + work.getTimeStart());
		System.out.println("TimeEnd\t|\t" + work.getTimeEnd());
		System.out.println("DateWork\t|\t" + work.getDateWork());
		System.out.println();
		
		return service.modifyWork(work);
	}
	
	@RequestMapping(value = "/work/{id_workerinfo}", method = RequestMethod.DELETE)
	public Boolean deleteWorkByIdWorkerInfo(@PathVariable int id_workerinfo) {
		return service.deleteWorksByIdWorkerInfo(id_workerinfo);
	}
	
	@RequestMapping(value = "/work/id_branch/{id_branch}", method = RequestMethod.GET)
	public List<WorkerInfoVO> getWorkersByIdBranch(@PathVariable int id_branch) {
		return service.getWorkersByIdBranch(id_branch);
	}

}

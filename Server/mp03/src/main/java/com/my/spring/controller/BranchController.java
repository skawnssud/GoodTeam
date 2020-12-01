package com.my.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.BranchVO;
import com.my.spring.service.BranchService;

@RestController
@RequestMapping("/branch")
public class BranchController {
	@Autowired
	private BranchService service;
	
	@RequestMapping("/all")
	public List<BranchVO> viewAll() {
		return service.viewAll();
	}
	
	@RequestMapping(value = "/title/{title}", method = RequestMethod.GET)
	public BranchVO searchByTitle(@PathVariable String title) {
		if (service.searchByTitle(title) == null) {
			BranchVO none = new BranchVO();
			none.setTitle("none");
			return none;
		} else {
			return service.searchByTitle(title);
		}
	}
	
	@RequestMapping(value = "/id_boss/{id_boss}", method = RequestMethod.GET)
	public List<BranchVO> searchByIdBoss(@PathVariable int id_boss) {
		System.out.println("searching id_boss: " + id_boss);
		if (service.searchByIdBoss(id_boss) == null) {
			List nonee = new ArrayList();
			return nonee;
		} else {
			return service.searchByIdBoss(id_boss);
		}
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Boolean createBranch(@RequestBody BranchVO newBranch) {
		System.out.println(newBranch.getId());
		System.out.println(newBranch.getTitle());
		System.out.println(newBranch.getId_boss());
		return service.createBranch(newBranch);
	}
	
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public Boolean deleteBranch(@RequestBody BranchVO target) {
		return service.deleteBranch(target);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Boolean modifyBranch(@RequestBody BranchVO newBranch) {
		return service.modifyBranch(newBranch);
	}
	
	
}

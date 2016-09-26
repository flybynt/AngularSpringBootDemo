package com.example.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.DataEntity;
import com.example.repository.DataRepository;

// SPRING REST controller example
//
// Explicit REST verb to repository operation mappings for DataEntity with path parameter mappings w/o meta-data 
// Examples URLs: 
// http://localhost:8080/ctlrdemo/all
// http://localhost:8080/ctlrdemo/firstName/Wiley
// http://localhost:8080/ctlrdemo/lastName/Coyote
// http://localhost:8080/ctlrdemo/id/1
//
@RestController
@RequestMapping(path="ctlrdemo", method=RequestMethod.GET, produces="application/json")
public class DataController {
	private final DataRepository dataRepository;
	
	@Autowired
	public DataController(DataRepository dr) {
		System.out.println("Entering DataControler(DataRepository dr) constructor");
		this.dataRepository = dr;
	}
	
	@RequestMapping(path="/all", method=RequestMethod.GET, produces="application/json")
	Collection<DataEntity> findAll() {
		return this.dataRepository.findAll();
	}
	
	@RequestMapping(path="/firstName/{fn}", method=RequestMethod.GET, produces="application/json")
	Collection<DataEntity> findByFirstName(@PathVariable String fn) {
		return this.dataRepository.findByFirstName(fn);
	}
	
	@RequestMapping(path="/lastName/{ln}", method=RequestMethod.GET, produces="application/json")
	Collection<DataEntity> findByLastName(@PathVariable String ln) {
		return this.dataRepository.findByLastName(ln);
	}
	
	@RequestMapping(path="/id/{id}", method=RequestMethod.GET, produces="application/json")
	DataEntity findById(@PathVariable Long id) {
		return this.dataRepository.findOne(id);
	}
}


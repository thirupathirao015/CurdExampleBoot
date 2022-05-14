package com.curd.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curd.demo.model.Employee;
import com.curd.demo.repository.EmployeeRepository;

@RestController
public class EmployeeController {
   
	@Autowired
	EmployeeRepository repo;
	
	//http://localhost:8015/hello
	@GetMapping("/hello")
	public String gethelloworld() {
		return "Welcome spring boot class";
	}
	  @PostMapping("save")
	  public Employee saveEmployee(@RequestBody Employee emp) {		  
		return repo.save(emp);		  
	  }
	  
	  @GetMapping("all")
	  public List<Employee> getAllEmployeesData(){
		 List<Employee> alldata = (List<Employee>)repo.findAll();
		return alldata;
	  }
	  
	  @GetMapping("/employee/{id}")
	  public Employee getEmployee(@PathVariable int id) {
		  Employee emp = (Employee)repo.findById(id).get();
		return emp;
	  }
	  
	  @SuppressWarnings("unused")
	@PutMapping("/employee/{id}")
	  public Employee putEmployee(@RequestBody Employee emp, @PathVariable int id) {
		  /*
		   Example -1 Working...
		   Integer value = null;
		  Employee employee = null;
		  try {
		   employee = (Employee)repo.findById(id).get();
		   value = new Integer(employee.getEid());
		  } catch(Exception e) {
			  e.printStackTrace();
		  }		  
		  if(value != null) { 
			  employee.setEname(emp.getEname());
			  employee.setAge(emp.getAge());
			  return repo.save(employee);
		  } else {
			  System.out.println("object not availble");
			  return repo.save(emp);
		  }
		  */
		  //Example -2 update and save
		  return repo.findById(id).map(employee -> {
			  employee.setEname(emp.getEname());
			  employee.setAge(emp.getAge());
	            return repo.save(employee);
	        }).orElseGet(() -> {
	        	emp.setEid(id);
	            return repo.save(emp);
	        });
		  
	  }
	  
}

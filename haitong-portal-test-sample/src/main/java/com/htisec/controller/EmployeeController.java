package com.htisec.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.htisec.model.User;
import com.javainuse.model.Employee;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
public class EmployeeController {

	private List<User> employees = createList();

	@GetMapping(value = "/employees", produces = "application/json")
	public List<User> firstPage() {
		return employees;
	}

	@GetMapping(path = { "/{id}" })
	public String delete(@PathVariable("id") int id) {
		for (User emp : employees) {
			if (emp.getUserId()==id) {
				return emp.getName();
			}
		}
		return "No User";
	}
//	@DeleteMapping(path = { "/{id}" })
//	public User delete(@PathVariable("id") int id) {
//		User deletedEmp = null;
//		for (User emp : employees) {
//			if (emp.getUserId().equals(id)) {
//				employees.remove(emp);
//				deletedEmp = emp;
//				break;
//			}
//		}
//		return deletedEmp;
//	}

	@PostMapping
	public User create(@RequestBody User user) {
		employees.add(user);
		System.out.println(employees);
		return user;
	}

	private static List<User> createList() {
		List<User> tempEmployees = new ArrayList<>();
		User emp1 = new User();
		emp1.setName("emp1");
		emp1.setUserId(1);

		User emp2 = new User();
		emp2.setName("emp2");
		emp2.setUserId(2);
		tempEmployees.add(emp1);
		tempEmployees.add(emp2);
		return tempEmployees;
	}
}
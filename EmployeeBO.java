package com.pp.BO;

import java.util.List;

import com.pp.models.Employee;

public interface EmployeeBO {
	
	int save(Employee e);
	int delete(int id);
	int delete(Employee e);
	int update(Employee e);
	Employee get(int id);
	List<Employee> getAll();

}

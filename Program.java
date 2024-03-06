package com.pp.app;

import java.util.List;

import com.pp.BO.EmployeeBOImpl;
import com.pp.models.Employee;

public class Program {

	public static void main(String[] args) {
      EmployeeBOImpl empbo = new EmployeeBOImpl();
      
     Employee employee=empbo.get(1);//get employee of id 1
      System.out.println(employee);
      
      employee.setDepartment("IT");//change dept in object
      empbo.update(employee);//change dept in database
    
      
      
      
      List<Employee> employees = empbo.getAll();
      
      for(Employee employee1 : employees) {
    	  System.out.println(employee1);// get all employees list
    	  
    	  
    	  
      }
	}

}

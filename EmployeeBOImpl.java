package com.pp.BO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pp.models.Employee;

public class EmployeeBOImpl implements EmployeeBO {
	private static Connection connection=null;
	private static PreparedStatement prepareStatement=null;
	private static Statement statement=null;
	private static ResultSet res = null;
	

	private final static String INSERT_QUERY = "insert into`employee` (`id`, `name`, `email`, `department`, `salary`) values (?,?,?,?,?)";
	private final static String DELETE_QUERY = "delete from `employee` where `id` = ?";
	private final static String UPDATE_QUERY = "update `employee` set `name` = ?, `email` =?, `department`=?, `salary` = ? where `id`=? ";
	private final static String SELECT_QUERY = "select * from`employee` where `id` = ?";
	private final static String SELECT_ALL_QUERY = "select * from`employee`";
	

	public EmployeeBOImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//load the Driver 
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcclasses","root","root");//get connection from database
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int save(Employee e) {
           int i=0;
           
		try {
			
			prepareStatement=connection.prepareStatement(INSERT_QUERY);
			
			prepareStatement.setInt(1, e.getId());
			prepareStatement.setString(2, e.getName());
			prepareStatement.setString(3, e.getEmail());
			prepareStatement.setString(4, e.getDepartment());
			prepareStatement.setInt(5, e.getSalary());
			
			i=prepareStatement.executeUpdate();	//Execute SQL statement		
			
		} catch ( SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
				
		return i;
	}

	@Override
	public int delete(int id) {
		
	    try {
			prepareStatement =  connection.prepareStatement(DELETE_QUERY);
			prepareStatement.setInt(1, id);
		    return prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(Employee e) {
		return delete(e.getId());
	}

	@Override
	public int update(Employee e) {
		try {
			prepareStatement =  connection.prepareStatement(UPDATE_QUERY);
		
		prepareStatement.setString(1, e.getName());
		prepareStatement.setString(1, e.getEmail());
		prepareStatement.setString(1, e.getDepartment());
		prepareStatement.setInt(1, e.getSalary());
		prepareStatement.setInt(1, e.getId());
		
	    return prepareStatement.executeUpdate();
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public Employee get(int id) {
		try {
			prepareStatement =  connection.prepareStatement(SELECT_QUERY);
		prepareStatement.setInt(1, id);
	    res = prepareStatement.executeQuery();
	    
	    if(res.next()) {
			int id1 = res.getInt("id");
			String name=res.getString("name");
			String email=res.getString("email");
			String department=res.getString("department");
			int salary=res.getInt("salary");
			
			return new Employee(id1, name, email, department, salary);
			
		 }
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> getAll() {

		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			 statement = connection.createStatement();
			 res = statement.executeQuery(SELECT_ALL_QUERY);
			 
			 while(res.next()) {
				int id = res.getInt("id");
				String name=res.getString("name");
				String email=res.getString("email");
				String department=res.getString("department");
				int salary=res.getInt("salary");
				
				Employee e =  new Employee(id, name, email, department, salary);
				
				employees.add(e);

			 }
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
	}

}

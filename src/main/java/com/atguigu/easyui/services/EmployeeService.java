package com.atguigu.easyui.services;

import java.util.List;

import com.atguigu.easyui.entities.Employee;

public interface EmployeeService {

	List<Employee> getAllEmp();

	List<Employee> getEmpPageList(int pageNum, int pageSize);

	void saveEmployee(Employee employee);

	void removeEmployee(Integer empId);

	void updateEmployee(Employee employee);

}

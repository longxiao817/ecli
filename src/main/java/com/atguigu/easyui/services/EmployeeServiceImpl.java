package com.atguigu.easyui.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.easyui.entities.Employee;
import com.atguigu.easyui.mappers.EmployeeMapper;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(readOnly=true)
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public List<Employee> getAllEmp() {
		return employeeMapper.selectAll();
	}

	@Override
	public List<Employee> getEmpPageList(int pageNum, int pageSize) {
		
		PageHelper.startPage(pageNum, pageSize);
		
		return employeeMapper.selectAll();
	}

	@Override
	@Transactional(readOnly=false)
	public void saveEmployee(Employee employee) {
		employeeMapper.insert(employee);
	}

	@Override
	@Transactional(readOnly=false)
	public void removeEmployee(Integer empId) {
		employeeMapper.deleteByPrimaryKey(empId);
	}

	@Override
	@Transactional(readOnly=false)
	public void updateEmployee(Employee employee) {
		employeeMapper.updateByPrimaryKey(employee);
	}

}

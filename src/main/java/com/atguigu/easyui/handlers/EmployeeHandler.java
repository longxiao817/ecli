package com.atguigu.easyui.handlers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.easyui.entities.Employee;
import com.atguigu.easyui.services.EmployeeService;
import com.github.pagehelper.Page;
import com.mysql.fabric.Response;


@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeService employeeService;
	
	@ResponseBody
	@RequestMapping("/list")
	public Map<String,Object> getList(@RequestParam("page")int pageNum, @RequestParam("rows")int pageSize){
	    List<Employee> list=employeeService.getEmpPageList(pageNum, pageSize);
	    System.out.println(list);
	    
	    Page<Employee> page = (Page<Employee>)list;
        
        //获取总记录数
        long total = page.getTotal();
        
        //创建Map对象用来存放响应数据
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("total", total);
        jsonMap.put("rows", list);
        
        return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping("/listAll")
	public List<Employee> getListAll(){
	    return employeeService.getAllEmp();
	}
	
	@RequestMapping("/add")
	public void addData(Employee employee,HttpServletResponse response){
	    employeeService.saveEmployee(employee);
	    try {
            response.getWriter().write("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	@RequestMapping("/edit")
	public void editData(Employee employee,HttpServletResponse response){
	    System.out.println(employee);
	    employeeService.updateEmployee(employee);
	    try {
            response.getWriter().write("success");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	@RequestMapping("/removeEmp/{empId}")
	public void remove(@PathVariable("empId")Integer empId,HttpServletResponse response){
	    employeeService.removeEmployee(empId);
	    try {
            response.getWriter().write("success");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}

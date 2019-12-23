package com.berat.service.employee.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berat.dao.employee.EmployeeRepository;
import com.berat.domain.employee.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeRepository {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public boolean saveEmployee(Employee employee) {
		return employeeRepository.saveEmployee(employee);
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		return employeeRepository.deleteEmployee(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.updateEmployee(employee);
	}

	@Override
	public Employee findEmployeeById(long employeeId) {
		return employeeRepository.findEmployeeById(employeeId);
	}

	@Override
	public long countEmployee() {
		return employeeRepository.countEmployee();
	}

	@Override
	public List<Employee> findAllEmployees() {
		return employeeRepository.findAllEmployees();
	}

	@Override
	public List<Employee> findIntervalBetweenEmployees(int first, int max) {
		return employeeRepository.findIntervalBetweenEmployees(first, max);
	}

}

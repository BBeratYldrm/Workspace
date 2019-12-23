package com.berat.service.employee;

import java.util.List;

import com.berat.domain.employee.Employee;

public interface EmployeeService {

	boolean saveEmployee(Employee employee);

	boolean deleteEmployee(Employee employee);

	Employee updateEmployee(Employee employee);

	Employee findEmployeeById(long employeeId);

	long countEmployee();

	List<Employee> findAllEmployees();

	List<Employee> findIntervalBetweenEmployees(int first, int max);

}

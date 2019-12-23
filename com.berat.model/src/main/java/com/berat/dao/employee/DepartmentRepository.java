package com.berat.dao.employee;

import java.util.List;

import com.berat.domain.employee.Department;

public interface DepartmentRepository {

	boolean saveDepartment(Department department);

	boolean deleteDepartment(Department department);

	Department updateDepartment(Department department);

	Department findDeparmentById(long departmentId);

	List<Department> findAllDepartments();

	List<String> findDepartmentNames();
}

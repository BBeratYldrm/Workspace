package com.berat.service.employee.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berat.dao.employee.DepartmentRepository;
import com.berat.domain.employee.Department;
import com.berat.service.employee.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public boolean saveDepartment(Department department) {
		return departmentRepository.saveDepartment(department);
	}

	@Override
	public boolean deleteDepartment(Department department) {
		return departmentRepository.deleteDepartment(department);
	}

	@Override
	public Department updateDepartment(Department department) {
		return departmentRepository.updateDepartment(department);
	}

	@Override
	public Department findDeparmentById(long departmentId) {
		return departmentRepository.findDeparmentById(departmentId);
	}

	@Override
	public List<Department> findAllDepartments() {
		return departmentRepository.findAllDepartments();
	}

	@Override
	public List<String> findDepartmentNames() {
		return departmentRepository.findDepartmentNames();
	}

}

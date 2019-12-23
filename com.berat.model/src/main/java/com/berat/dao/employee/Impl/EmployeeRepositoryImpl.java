package com.berat.dao.employee.Impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.berat.dao.employee.EmployeeRepository;
import com.berat.domain.employee.Employee;

@Repository
@Transactional
public class EmployeeRepositoryImpl implements EmployeeRepository {

	private EntityManager entityManager;

	@Override
	public boolean saveEmployee(Employee employee) {
		entityManager.persist(employee);
		return false;
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		if (entityManager.contains(employee)) {
			entityManager.remove(employee);
		} else {
			Employee deleteEmployee = findEmployeeById(employee.getEmployeeId());
			entityManager.remove(deleteEmployee);

		}
		return true;

	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Employee updatedEmployee = entityManager.merge(employee);
		return updatedEmployee;
	}

	@Override
	public Employee findEmployeeById(long employeeId) {
		return entityManager.createNamedQuery("Employee.findFullById", Employee.class)
				.setParameter("employeeId", employeeId).getSingleResult();
	}

	@Override
	public List<Employee> findAllEmployees() {
		return entityManager.createNamedQuery("Employee.findAll", Employee.class).getResultList();
	}

	@Override
	public long countEmployee() {
		return entityManager.createNamedQuery("Employee.count", long.class).getSingleResult();
	}

	@Override
	public List<Employee> findIntervalBetweenEmployees(int first, int max) {

		return entityManager.createNamedQuery("Employee.findAll", Employee.class).setFirstResult(first)
				.setMaxResults(max).getResultList();
	}

}

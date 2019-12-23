package com.berat.dao.employee.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.berat.dao.employee.DepartmentRepository;
import com.berat.domain.employee.Department;

@Repository
@Transactional
public class DepartmentRepositoryImpl implements DepartmentRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean saveDepartment(Department department) {

		entityManager.persist(department);

		return true;
	}

	@Override
	public boolean deleteDepartment(Department department) {
		if (entityManager.contains(department)) {
			entityManager.remove(department);
			return true;
		}
		Department deleteDepartment = findDeparmentById(department.getDepartmentId());
		entityManager.remove(deleteDepartment);
		return true;

	}

	@Override
	public Department updateDepartment(Department department) {
		Department updatedDepartment = entityManager.merge(department);
		entityManager.flush();
		return updatedDepartment;
	}

	@Override
	public Department findDeparmentById(long departmentId) {
		TypedQuery<Department> typedQuery = entityManager.createNamedQuery("Department.findEmployeeAndLocationById", Department.class);
		typedQuery.setParameter("departmentId", departmentId);
		return typedQuery.getSingleResult();
	}

	@Override
	public List<Department> findAllDepartments() {
		return entityManager.createNamedQuery("Department.findAll", Department.class).getResultList();
	}

	@Override
	public List<String> findDepartmentNames() {
		TypedQuery<String> typedQuery = entityManager.createNamedQuery("Department.findByDepartmentName", String.class);
		return typedQuery.getResultList();
	}

}

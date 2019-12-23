package com.berat.dao.user.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.berat.dao.user.RoleRepository;
import com.berat.domain.user.Role;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Role saveRole(Role role) {
		entityManager.persist(role);
		return role;
	}

	@Override
	public Role updateRole(Role role) {
		Role updatedRole = entityManager.merge(role);
		entityManager.flush();
		return updatedRole;
	}

	@Override
	public Role deleteRole(Role role) {
		if (entityManager.contains(role)) {
			entityManager.remove(role);
			return role;
		} else {
			Role deleteRole = findRoleById(role.getRoleId());
			entityManager.remove(deleteRole);
			return role;
		}

	}

	@Override
	public Role findRoleById(long id) {
		TypedQuery<Role> typedQuery = entityManager.createNamedQuery("Role.findRoleById", Role.class);
		return typedQuery.setParameter("roleId", id).getSingleResult();
	}

	@Override
	public Role findRoleByName(String roleName) {
		return entityManager.createNamedQuery("Role.findAllRolesByName", Role.class).setParameter("roleName", roleName).getSingleResult();
	}

	@Override
	public List<Role> findAllRoles() {
		return entityManager.createNamedQuery("Role.findAllRoles", Role.class).getResultList();
	}

}

package com.berat.dao.user.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.berat.dao.user.PrivilegeRepository;
import com.berat.domain.user.Privilege;

@Repository
@Transactional
public class PrivilegeRepositoryImpl implements PrivilegeRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Privilege savePrivilege(Privilege privilege) {
		entityManager.persist(privilege);
		return privilege;
	}

	@Override
	public Privilege updatePrivilege(Privilege privilege) {
		Privilege updatedPrivilege = entityManager.merge(privilege);
		entityManager.flush();
		return updatedPrivilege;
	}

	@Override
	public Privilege deletePrivilege(Privilege privilege) {
		if (entityManager.contains(privilege)) {
			entityManager.remove(privilege);
			return privilege;
		} else {
			Privilege deletePrivilege = findPrivilegeById(privilege.getPrivilegeId());
			entityManager.remove(deletePrivilege);
			return deletePrivilege;
		}

	}

	@Override
	public Privilege findPrivilegeById(long id) {
		TypedQuery<Privilege> typedQuery = entityManager.createNamedQuery("Privilege.findPrivilegeById",
				Privilege.class);
		return typedQuery.setParameter("privilegeId", id).getSingleResult();
	}

	@Override
	public Privilege findPrivilegeByName(String privilegeName) {
		TypedQuery<Privilege> typedQuery = entityManager.createNamedQuery("Privilege.findPrivilegeByName",
				Privilege.class);
		return typedQuery.setParameter("privilegeName", privilegeName).getSingleResult();
	}

	@Override
	public List<Privilege> findAllPrivileges() {
		return entityManager.createNamedQuery("Privilege.findAllPrivileges", Privilege.class).getResultList();
	}

}

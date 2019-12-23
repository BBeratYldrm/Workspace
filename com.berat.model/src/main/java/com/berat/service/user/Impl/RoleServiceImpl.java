package com.berat.service.user.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berat.dao.user.RoleRepository;
import com.berat.domain.user.Role;
import com.berat.service.user.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role saveRole(Role role) {
		return roleRepository.saveRole(role);
	}

	@Override
	public Role updateRole(Role role) {
		return roleRepository.updateRole(role);
	}

	@Override
	public Role deleteRole(Role role) {
		return roleRepository.deleteRole(role);
	}

	@Override
	public Role findRoleById(long id) {
		return roleRepository.findRoleById(id);
	}

	@Override
	public Role findRoleByName(String roleName) {
		return roleRepository.findRoleByName(roleName);
	}

	@Override
	public List<Role> findAllRoles() {
		return roleRepository.findAllRoles();
	}

}

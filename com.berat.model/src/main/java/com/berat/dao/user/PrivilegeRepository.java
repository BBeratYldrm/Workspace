package com.berat.dao.user;

import java.util.List;

import com.berat.domain.user.Privilege;

public interface PrivilegeRepository {

	Privilege savePrivilege(Privilege privilege);

	Privilege updatePrivilege(Privilege privilege);

	Privilege deletePrivilege(Privilege privilege);

	Privilege findPrivilegeById(long id);

	Privilege findPrivilegeByName(String privilegeName);

	List<Privilege> findAllPrivileges();
}

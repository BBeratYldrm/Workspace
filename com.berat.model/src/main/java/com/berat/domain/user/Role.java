package com.berat.domain.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "Role.findAllRoles", query = "SELECT r FROM Role r"),
	@NamedQuery(name = "Role.findAllRolesByName", query = "SELECT r.roleName FROM Role r"),
	@NamedQuery(name = "Role.findRoleById", query = "SELECT r.roleId FROM Role r")

})
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long roleId;

	@Column
	private String roleName;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Roles_Privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
	private List<Privilege> rolePrivileges;

	@OneToMany(mappedBy = "role")
	private List<User> roleUsers;

	public Role(String roleName, List<Privilege> rolePrivileges, List<User> roleUsers) {
		this.roleName = roleName;
		this.rolePrivileges = rolePrivileges;
		this.roleUsers = roleUsers;
	}

	public Role() {
		super();
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Privilege> getRolePrivileges() {
		return rolePrivileges;
	}

	public void setRolePrivileges(List<Privilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}

	public List<User> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(List<User> roleUsers) {
		this.roleUsers = roleUsers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (roleId ^ (roleId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (roleId != other.roleId)
			return false;
		return true;
	}

}

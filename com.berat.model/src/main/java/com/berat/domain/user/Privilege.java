package com.berat.domain.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ 
		@NamedQuery(name = "Privilege.findAllPrivileges", query = "SELECT p FROM Privilege p"),
		@NamedQuery(name = "Privilege.findPrivilegeByName", query = "SELECT p.privilegeName FROM Privilege p"),
		@NamedQuery(name = "Privilege.findPrivilegeById", query = "SELECT p.privilegeId FROM Privilege p")

})
public class Privilege {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "privilege_id")
	private long privilegeId;

	@Column(name = "privilege_name")
	private String privilegeName;

	@ManyToMany(mappedBy = "rolePrivileges")
	private List<Role> privilegeRoles;

	public Privilege(String privilegeName, List<Role> privilegeRoles) {
		this.privilegeName = privilegeName;
		this.privilegeRoles = privilegeRoles;
	}

	public Privilege() {

	}

	public long getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(long privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public List<Role> getPrivilegeRoles() {
		return privilegeRoles;
	}

	public void setPrivilegeRoles(List<Role> privilegeRoles) {
		this.privilegeRoles = privilegeRoles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (privilegeId ^ (privilegeId >>> 32));
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
		Privilege other = (Privilege) obj;
		if (privilegeId != other.privilegeId)
			return false;
		return true;
	}

}

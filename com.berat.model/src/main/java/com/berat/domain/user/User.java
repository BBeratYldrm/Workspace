package com.berat.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "User.findAllUsers", query = "SELECT u FROM User u"),
	@NamedQuery(name = "User.findUserById", query = "SELECT u.userId FROM User u"),
	@NamedQuery(name = "User.findUserByEmail", query = "SELECT u.eMail FROM User u"),
	@NamedQuery(name = "User.findUserByUsername", query = "SELECT u.userName FROM User u")
})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long userId;

	private String firstName;

	private String lastName;

	@Column(nullable = false, unique = true)
	private String userName;

	@Column
	private String eMail;

	@Column(length = 60)
	private String userPassword;

	private boolean enabled;

	private boolean accountExpired;

	private boolean credentialsNonExpired;

	private boolean accountNonLocked;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;

	public User(String firstName, String lastName, String userName, String eMail, String userPassword, boolean enabled,
			boolean accountExpired, boolean credentialsNonExpired, boolean accountNonLocked, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.eMail = eMail;
		this.userPassword = userPassword;
		this.enabled = false;
		this.accountExpired = true;
		this.credentialsNonExpired = true;
		this.accountNonLocked = true;
		this.role = role;
	}

	public User() {
		this.enabled = false;
		this.accountExpired = true;
		this.credentialsNonExpired = true;
		this.accountNonLocked = true;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		User other = (User) obj;
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}

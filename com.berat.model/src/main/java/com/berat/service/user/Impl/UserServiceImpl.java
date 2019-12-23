package com.berat.service.user.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.berat.dao.user.RoleRepository;
import com.berat.dao.user.UserRepository;
import com.berat.domain.user.Role;
import com.berat.domain.user.User;
import com.berat.service.exception.ServiceException;
import com.berat.service.user.UserService;
import com.berat.web.DataTO.UserDataTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User saveUser(User user) {
		return userRepository.saveUser(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.updateUser(user);
	}

	@Override
	public User deleteUser(User user) {
		return userRepository.deleteUser(user);
	}

	@Override
	public User findUserById(long id) {
		return userRepository.findUserById(id);
	}

	@Override
	public User findUserByEmail(String eMail) {
		return userRepository.findUserByEmail(eMail);
	}

	@Override
	public User findUserByName(String userName) {
		return userRepository.findUserByName(userName);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAllUsers();
	}

	// Formdan gelen veriler UserDataTO nesnemize aktarýlýr. Doðrulamalar yapýlýr ve
	// buraya gelir.
	// Burada gelen userDataTO nun unique olup olmadýðýný kontrol edicez.

	private boolean userNameExist(String userName) {
		return findUserByName(userName) != null;

	}

	@Override
	public User registerNewUser(UserDataTO userDataTO) {

		boolean status = userNameExist(userDataTO.getUserName());
		if (status) {
			throw new ServiceException("Kullanýcý Adý daha önceden alýnmýþ." + userDataTO.getUserName());
		}
		Role role = roleRepository.findRoleByName("USER_ROLES");
		User user = new User();
		user.setFirstName(userDataTO.getFirstName());
		user.setLastName(userDataTO.getLastName());
		user.setUserName(userDataTO.getUserName());
		user.seteMail(userDataTO.geteMail());
		user.setUserPassword(passwordEncoder.encode(userDataTO.getPassword()));
		user.setRole(role);
		return userRepository.saveUser(user);
	}

	// Eski þifresi ve yeni þifresi eþleþiyor mu ?

	@Override
	public boolean checkValidOldPassword(User user, String oldPassword) {
		return passwordEncoder.matches(oldPassword, user.getUserPassword());
	}

	// Þifreler eþleþiyorsa userýn yeni þifresini update ettik.

	@Override
	public void changeUserPassword(User user, String password) {
		user.setUserPassword(passwordEncoder.encode(password));
		userRepository.updateUser(user);
	}

}

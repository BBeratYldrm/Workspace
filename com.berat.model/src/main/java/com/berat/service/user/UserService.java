package com.berat.service.user;

import java.util.List;

import com.berat.domain.user.User;
import com.berat.web.DataTO.UserDataTO;

public interface UserService {
	
	User registerNewUser (UserDataTO userDataTO);
	
	boolean checkValidOldPassword (User user, String oldPassword);
	
	void changeUserPassword (User user , String password);

	User saveUser(User user);

	User updateUser(User user);

	User deleteUser(User user);

	User findUserById(long id);

	User findUserByEmail(String eMail);

	User findUserByName(String userName);

	List<User> findAllUsers();

}

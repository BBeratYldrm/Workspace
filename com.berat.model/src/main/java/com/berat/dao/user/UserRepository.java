package com.berat.dao.user;

import java.util.List;

import com.berat.domain.user.User;

public interface UserRepository {

	User saveUser(User user);

	User updateUser(User user);

	User deleteUser(User user);

	User findUserById(long id);

	User findUserByEmail(String eMail);

	User findUserByName(String userName);

	List<User> findAllUsers();

}

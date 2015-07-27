package com.gbros.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbros.modle.User;

@Service("userService")
// 在spring容器中声明service，同时把这个service放到spring容器中在命名为userservice
public class UserService {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserDao userDao;

	public User getUserById(String userId) {
		return userDao.selectUserById(userId);
	}

	public void deleteUser(String userId) {
		userDao.deleteById(userId);
	}

	public void updateUser(String userId, String name) {
		userDao.updateById(userId, name);
	}

	public void saveUser(String userId, String name) {
		userDao.insertUser(userId, name);
	}

	public List<User> getUserList() {

		return userDao.selectuserlist();
	}
}

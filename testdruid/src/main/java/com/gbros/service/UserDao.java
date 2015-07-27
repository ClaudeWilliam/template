package com.gbros.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidDataSource;
import com.gbros.modle.User;

@Repository
// 声明是数据库的类，将数据库的Dao类放到spring容器中
public class UserDao {

	@Autowired
	// 自动填充datasource的类
	private DruidDataSource dataSource;

	// 根据ID查找user
	public User selectUserById(String userId) {
		User user = new User();
		String sql = "SELECT name from user where id=? ;";
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setObject(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user.setId(userId);
				user.setName(resultSet.getString(1));
			}
			relase(conn, resultSet, preparedStatement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("---------->select failed...........");
		}
		return user;

	}

	// 插入一条user的数据
	public void insertUser(Object... args) {
		String sql = "INSERT into user(id,name) values(?,?)";
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			preparedStatement.executeUpdate();
			relase(conn, null, preparedStatement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("---------->insert failed..........");
		}
	}

	// 删除一条user的数据根据ID
	public void deleteById(String userId) {
		String sql = "delete from user where id=?;";
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setObject(1, userId);
			preparedStatement.executeUpdate();
			relase(conn, null, preparedStatement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("----------->delete failed.........");
		}
	}

	// 更新一组数据根据ID
	public void updateById(String userId, String name) {
		String sql = "update user set name=?  where id=?;";
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setObject(1, name);
			preparedStatement.setObject(2, userId);
			preparedStatement.executeUpdate();
			relase(conn, null, preparedStatement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("---------------->update failed");
		}

	}

	// 返回所有数据，以json的形式
	public List<User> selectuserlist() {

		String sql = "SELECT * from user  ;";
		List<User> users = new ArrayList<User>();
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			;
			ResultSet resultSet = preparedStatement.executeQuery();
			{
				while (resultSet.next()) {
					User user = new User();
					user.setId(resultSet.getString("id"));
					user.setName(resultSet.getString("name"));
					users.add(user);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("---------->select failed...........");
		}
		return users;

	}

	// 释放数据库connection，resultset，statement
	public void relase(Connection connection, ResultSet resultSet,
			Statement statement) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

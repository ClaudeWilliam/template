package com.gbros.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DBConfig {

	@Autowired
	DBParam dBConfig;//这是个自动填充的DBParam对象
	//DataSource dataSource;

	@Bean
	//把dataSource()放到spring的容器中
	public DataSource dataSource() throws SQLException {

		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(dBConfig.getDriverClassName());
		druidDataSource.setUrl(dBConfig.getUrl());
		druidDataSource.setUsername(dBConfig.getUserName());
		druidDataSource.setPassword(dBConfig.getPassword());
		druidDataSource.setMaxActive(dBConfig.getMaxActive());
		druidDataSource.setMinIdle(dBConfig.getMinIdle());
		druidDataSource.setInitialSize(dBConfig.getInitialSize());
		druidDataSource.setMaxWait(dBConfig.getMaxWait());
		druidDataSource.setValidationQuery(dBConfig.getValidationQuery());
		druidDataSource.setTestOnBorrow(dBConfig.isTestOnBorrow());
		druidDataSource.setTestOnReturn(dBConfig.isTestOnReturn());
		druidDataSource.setTestWhileIdle(dBConfig.isTestWhileIdle());
		druidDataSource.setTimeBetweenEvictionRunsMillis(dBConfig
				.getTimeBetweenEvictionRunsMillis());
		druidDataSource.setMinEvictableIdleTimeMillis(dBConfig
				.getMinEvictableIdleTimeMillis());
		druidDataSource.setRemoveAbandoned(dBConfig.isRemoveAbandoned());
		druidDataSource.setRemoveAbandonedTimeout(dBConfig
				.getRemoveAbandonedTimeout());
		druidDataSource.setLogAbandoned(dBConfig.isLogAbandoned());
		druidDataSource.setFilters(dBConfig.getFilters());
		
		
		return druidDataSource;
	}
}

package com.springboot_test.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Springboot04DataApplicationTests {
@Autowired
	DataSource dataSource;
	@Test
	void contextLoads() throws SQLException {
		//查看默认的数据源
		System.out.println(dataSource.getClass());
		//获取链接
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		DruidDataSource druidDataSource = (DruidDataSource) dataSource;
		System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
		System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());
		//关闭
		connection.close();
	}

}

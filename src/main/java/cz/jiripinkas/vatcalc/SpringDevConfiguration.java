package cz.jiripinkas.vatcalc;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class SpringDevConfiguration {

	@Bean
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl("jdbc:hsqldb:mem:test;sql.syntax_pgs=true");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		return dataSource;
	}
	
}

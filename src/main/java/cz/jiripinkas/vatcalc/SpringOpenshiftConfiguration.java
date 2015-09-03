package cz.jiripinkas.vatcalc;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//@Profile("openshift")
//@Configuration
public class SpringOpenshiftConfiguration {

	@Bean
	public DataSource dataSource() {
		String url = "jdbc:postgresql://" + System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST") + ":" + System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT") + "/" + System.getenv("OPENSHIFT_APP_NAME");
		String username = System.getenv("OPENSHIFT_POSTGRESQL_DB_USERNAME");
		String password = System.getenv("OPENSHIFT_POSTGRESQL_DB_PASSWORD");
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		config.setDriverClassName("org.postgresql.Driver");
		config.setMaximumPoolSize(5);
		return new HikariDataSource(config);
	}

}

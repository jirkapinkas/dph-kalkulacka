package cz.jiripinkas.vatcalc;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariDataSource;

@Profile("prod")
@Configuration
public class SpringProdConfiguration {

	@Bean
	public DataSource dataSource() throws URISyntaxException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(dbUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName("org.postgresql.Driver");
		return dataSource;
	}

}

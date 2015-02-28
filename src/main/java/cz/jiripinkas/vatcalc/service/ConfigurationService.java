package cz.jiripinkas.vatcalc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cz.jiripinkas.vatcalc.entity.Configuration;
import cz.jiripinkas.vatcalc.repository.ConfigurationRepository;

@Service
public class ConfigurationService {

	@Autowired
	private ConfigurationRepository configurationRepository;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String findAdminUsername() {
		Configuration configuration = configurationRepository.findAll().get(0);
		return configuration.getUsername();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void change(String username, String password) {
		Configuration configuration = configurationRepository.findAll().get(0);
		if (username != null && !username.isEmpty()) {
			configuration.setUsername(username);
		}
		if (username != null && !username.isEmpty()) {
			configuration.setPassword(new BCryptPasswordEncoder().encode(password));
		}
		configurationRepository.save(configuration);
	}

}

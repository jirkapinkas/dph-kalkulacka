package cz.jiripinkas.vatcalc.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.jiripinkas.vatcalc.entity.Configuration;
import cz.jiripinkas.vatcalc.entity.Item;
import cz.jiripinkas.vatcalc.repository.ConfigurationRepository;
import cz.jiripinkas.vatcalc.repository.ItemRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ConfigurationRepository configurationRepository;

	@PostConstruct
	public void init() {
		if (itemRepository.count() == 0) {
			Item item = new Item();
			item.setName("Kilometrická banka ČD");
			item.setDph(15);
			item.setCenaBezDph(1913.12);
			item.setCenaSDph(2200);
			item.setCastkaDph(286.88);
			item.setCenaBezDphDisabled(true);
			item.setVisible(true);
			itemRepository.save(item);
		}
		if (configurationRepository.count() == 0) {
			Configuration configuration = new Configuration();
			configuration.setUsername("admin");
			configuration.setPassword(new BCryptPasswordEncoder().encode("admin"));
			configurationRepository.save(configuration);
		}
	}
}

package cz.jiripinkas.vatcalc.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.jiripinkas.vatcalc.entity.Item;
import cz.jiripinkas.vatcalc.repository.ItemRepository;

@Transactional
@Service
public class InitDbService {
	
	@Autowired
	private ItemRepository itemRepository;

	@PostConstruct
	public void init() {
		Item item = new Item();
		item.setName("Kilometrická banka ČD");
		item.setDph(15);
		item.setCenaBezDph(1913.12);
		item.setCenaSDph(2200);
		item.setCastkaDph(286.88);
		item.setCenaBezDphDisabled(true);
		itemRepository.save(item);
	}
}

package cz.jiripinkas.vatcalc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.jiripinkas.vatcalc.entity.Item;
import cz.jiripinkas.vatcalc.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	public void save(Item item) {
		itemRepository.save(item);
	}

	public Item findOne(int id) {
		return itemRepository.findOne(id);
	}

	public void delete(int id) {
		itemRepository.delete(id);
	}

}

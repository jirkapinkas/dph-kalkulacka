package cz.jiripinkas.vatcalc.service;

import org.junit.Test;

import cz.jiripinkas.vatcalc.entity.Item;

public class ItemServiceTest {
	
	private ItemService itemService = new ItemService();

	@Test
	public void testValidateInputCenaBezDph() {
		Item item = new Item();
		item.setCenaSDphDisabled(true);
		item.setCenaBezDph(1001.01);
		item.setDph(10);
		item.setCastkaDph(100.1);
		item.setCenaSDph(1101.11);
		itemService.validateInput(item);
	}

	@Test
	public void testValidateInputCenaSDph() {
		Item item = new Item();
		item.setCenaBezDphDisabled(true);
		item.setCenaSDph(5001.1);
		item.setDph(10);
		item.setCastkaDph(454.6);
		item.setCenaBezDph(4546.5);
		itemService.validateInput(item);
	}

}

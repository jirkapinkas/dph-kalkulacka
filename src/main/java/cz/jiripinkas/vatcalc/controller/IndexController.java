package cz.jiripinkas.vatcalc.controller;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cz.jiripinkas.vatcalc.entity.Item;
import cz.jiripinkas.vatcalc.service.ItemService;

@Controller
public class IndexController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/index.html")
	public String index(Model model) {
		return "index";
	}

	@ResponseBody
	@RequestMapping("/items")
	public Data findAll() {
		return new Data(itemService.findAll());
	}

	@ResponseBody
	@RequestMapping("/items/{id}")
	public Item findOne(@PathVariable int id) {
		return itemService.findOne(id);
	}

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(@RequestParam String name, @RequestParam int dph, @RequestParam double cenaBezDph, @RequestParam double cenaSDph, @RequestParam boolean cenaBezDphDisabled,
			@RequestParam boolean cenaSDphDisabled, @RequestParam double castkaDph) {
		Item item = new Item();
		item.setDph(dph);
		item.setName(Jsoup.clean(name, Whitelist.none()));
		item.setCenaBezDph(cenaBezDph);
		item.setCenaSDph(cenaSDph);
		item.setCastkaDph(castkaDph);
		item.setCenaBezDphDisabled(cenaBezDphDisabled);
		item.setCenaSDphDisabled(cenaSDphDisabled);

		itemService.save(item);
	}

	@ResponseBody
	@RequestMapping(value = "/items/delete/{id}", method = RequestMethod.POST)
	public void remove(@PathVariable int id) {
		// TODO Only administrator should be able to remove items!!!
		itemService.delete(id);
	}

	public static class Data {

		public Data() {
		}

		public Data(Object data) {
			this.data = data;
		}

		private Object data;

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}
	}

}

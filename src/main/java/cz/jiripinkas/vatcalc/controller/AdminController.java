package cz.jiripinkas.vatcalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cz.jiripinkas.vatcalc.controller.IndexController.Data;
import cz.jiripinkas.vatcalc.service.ItemService;

@Controller
public class AdminController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping("/login.html")
	public String index() {
		return "login";
	}
	
	@ResponseBody
	@RequestMapping(value = "/items/delete/{id}", method = RequestMethod.POST)
	public void remove(@PathVariable int id) {
		itemService.delete(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/items/visible/{id}", method = RequestMethod.POST)
	public void setVisible(@PathVariable int id) {
		itemService.setVisible(id);
	}
	
	@RequestMapping("/admin/")
	public String administer() {
		return "admin";
	}

	@ResponseBody
	@RequestMapping("/invisible-items")
	public Data findInvisible() {
		return new Data(itemService.findInvisible());
	}

}

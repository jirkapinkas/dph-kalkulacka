package cz.jiripinkas.vatcalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cz.jiripinkas.vatcalc.service.ConfigurationService;

@Controller
public class AdminAccountController {
	
	@Autowired
	private ConfigurationService configurationService;

	@RequestMapping("/admin/account.html")
	public String account(Model model) {
		model.addAttribute("currentUsername", configurationService.findAdminUsername());
		return "admin-account";
	}

	@RequestMapping(value = "/admin/account.html", method = RequestMethod.POST)
	public String submitForm(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
		configurationService.change(username, password);
		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/admin/account.html";
	}

}

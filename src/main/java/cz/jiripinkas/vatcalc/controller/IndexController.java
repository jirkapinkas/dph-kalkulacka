package cz.jiripinkas.vatcalc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/index.html")
	public String index(Model model) {
		model.addAttribute("test", "hello from controller");
		return "index";
	}

}

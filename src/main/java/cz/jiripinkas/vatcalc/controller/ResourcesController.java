package cz.jiripinkas.vatcalc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResourcesController {

	@RequestMapping("/odkazy")
	public String resources() {
		return "resources";
	}
}

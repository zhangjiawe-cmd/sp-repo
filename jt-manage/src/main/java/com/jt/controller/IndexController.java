package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/page/{moduleName}")
	public String itemAdd(@PathVariable String moduleName) {
		return moduleName;
	}
}

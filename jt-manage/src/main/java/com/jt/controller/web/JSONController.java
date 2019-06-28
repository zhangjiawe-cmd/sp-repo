package com.jt.controller.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.util.ObjectMapperUtil;

@RestController
public class JSONController {
	@RequestMapping("/web/testJSONP")
	public String JSONP(String callback) {
		User user = new User();
		user.setId(100);
		user.setName("温呆鸡");
		String json=ObjectMapperUtil.toJson(user);
		return callback+"("+json+")";
	}
	private class User{
		private int id;
		private String name;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	}
}

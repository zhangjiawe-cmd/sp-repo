package com.jt.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;


@RestController
@RequestMapping("/web/item")
public class WebItemController {
	@Autowired
	private ItemService itemService;
	@RequestMapping("/findItemById")
	public Item findItemById(Long id) {
		return itemService.findItemById(id);
	}
	@RequestMapping("/findItemDescById")
	public ItemDesc findItemDescById(Long id) {
		return itemService.findItemDescById(id);
	}
}

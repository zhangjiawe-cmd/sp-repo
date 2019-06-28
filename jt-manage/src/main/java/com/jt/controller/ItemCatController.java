package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jt.annotation.RequiredCache;
import com.jt.enu.KEY_ENUM;
import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	//实现根据id查询商品分类信息
	@RequestMapping("/queryItemName")
	public String findItemCatNameById(Long itemCatId) {
		return itemCatService.findItemCatNameById(itemCatId);
	}

	/*
	 * //查询所有数据的商品分类信息
	 * 
	 * @RequestMapping("/list") public List<EasyUITree> findItemCatByParentId(){
	 * long parentId=0L;//查询一级商品分类信息 return
	 * itemCatService.findItemCatByParentId(parentId); }
	 */
	//@
	@RequestMapping("/list")
	@RequiredCache(key="ITEM_CAT",keyType=KEY_ENUM.AUTO)
	public List<EasyUITree> findItemCatByParentId(@RequestParam(value="id",defaultValue="0")Long parentId){
		//long parentId=0L;//查询一级商品分类信息
		return itemCatService.findItemCatByParentId(parentId);
		//return itemCatService.findItemCatByCache(parentId); 
	}
}

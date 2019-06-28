package com.jt.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUIData;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	@Override
	public EasyUIData findItemByPage(Integer page, Integer rows) {
		//1.获取商品记录总数
		int total=itemMapper.selectCount(null);
		/**
		 * 2.分页之后回传数据
		 * sql: select * from tb_item limit 起始位置,查询行数
		 * 第1页:  20
		 * 	select * from tb_item limit 0,20
		 * 第2页:  
		 * 	select * from tb_item limit 20,20
		 * 第3页:
		 *  select * from tb_item limit 40,20
		 * 第N页:
		 * 	 select * from tb_item 
		 * 			limit (page-1)rows,rows
		 */
		int start=(page-1)*rows;
		List<Item> itemList=itemMapper.findItemByPage(start,rows);
		return new EasyUIData(total, itemList);
	}
	@Transactional//添加事务控制
	@Override
	public void saveItem(Item item,ItemDesc itemDesc) {
		item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
		itemMapper.insert(item);
		itemDesc.setItemId(item.getId()).setCreated(item.getCreated()).setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void updateItem(Item item,ItemDesc itemDesc) {
		item.setCreated(new Date());
		itemMapper.updateById(item);
		
		itemDesc.setItemId(item.getId())
		.setUpdated(item.getUpdated());
		itemDescMapper.updateById(itemDesc);

	}
	@Transactional
	@Override
	public void deleteItem(Long[] ids) {
		//手动删除
		//itemMapper.deleteItem(ids);
		List idList=Arrays.asList(ids);
		itemMapper.deleteBatchIds(idList);
		itemDescMapper.deleteBatchIds(idList);
	}
	//update tb_item set status=#{status},created=#{} where id in {}
	@Override
	public void updateStatus(Long[] ids,Integer status) {
		Item item = new Item();
		item.setStatus(status).setCreated(new Date());
		UpdateWrapper<Item> updateWrapper = new UpdateWrapper<Item>();
		List<Long> longIds = Arrays.asList(ids);
		updateWrapper.in("id",longIds);
		itemMapper.update(item, updateWrapper);
	}
	@Override
	public ItemDesc findItemDescById(Long itemId) {

		return itemDescMapper.selectById(itemId);
	}
	@Override
	public Item findItemById(Long id) {
		Item item = itemMapper.selectById(id);
		
		return item;
	}







}

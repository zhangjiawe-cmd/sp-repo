package com.jt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.annotation.RequiredCache;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.EasyUITree;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private ItemCatMapper itemCatMapper;
	@Autowired
	private JedisCluster jedis;

	@Override
	public String findItemCatNameById(Long itemCatId) {
		ItemCat itemCat = itemCatMapper.selectById(itemCatId);
		return itemCat.getName() ;
	}
	/**
	 * 1.根据parentId查询数据库记录返回itemCatList集合
	 * 2.将itemCatList集合中的数据按照指定的格式封装为List<EasyUITree>
	 */
	@Override
	public List<EasyUITree> findItemCatByParentId(long parentId) {
		List<ItemCat> cartList = findItemCatList(parentId);
		List<EasyUITree> treeList = new ArrayList<>();
		//遍历集合数据,实现数据的转化
		for (ItemCat itemCat : cartList) {
			EasyUITree uiTree = new EasyUITree();
			uiTree.setId(itemCat.getId());
			uiTree.setText(itemCat.getName());
			String state=itemCat.getIsParent()?"closed":"open";
			uiTree.setState(state);
			treeList.add(uiTree);
		}
		return treeList;
	}
	
	public List<ItemCat> findItemCatList(Long parentId){
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<ItemCat>();
		queryWrapper.eq("parent_id", parentId);
		return itemCatMapper.selectList(queryWrapper);
	}
	/*@Override
	public List<EasyUITree> findItemCatByCache(Long parentId) {
		String key="ITEM_CAT_"+parentId;
		String result = jedis.get(key);
		List<EasyUITree> treeList = new ArrayList<>();
		if(StringUtils.isEmpty(result)) {
			 treeList = findItemCatByParentId(parentId);
			 String json = ObjectMapperUtil.toJson(treeList);
			 jedis.setex(key, 7*24*3600, json);
			 System.out.println("业务查询数据库");
		}else {
			 treeList = ObjectMapperUtil.toObject(result, treeList.getClass());
			 System.out.println("查询缓存");
		}
		return treeList;
	}*/
}

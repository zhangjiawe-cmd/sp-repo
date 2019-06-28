package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;

public interface ItemDescMapper extends BaseMapper<ItemDesc> {

	void updateById(Item item);

}

package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@TableName("tb_item_cat")
public class ItemCat extends BasePojo{
	@TableId(type=IdType.AUTO)
	private Long id;
	private Long parentId;
	private String name;
	private Integer status;
	private Integer sortOrder;
	private Boolean isParent;
	  /* id                   bigint not null auto_increment,
	   parent_id            bigint comment '父ID=0时，代表一级类目',
	   name                 varchar(150),
	   status               int(1) default 1 comment '默认值为1，可选值：1正常，2删除',
	   sort_order           int(4) not null,
	   is_parent            tinyint(1),
	   created              datetime,
	   updated              datetime,
	   primary key (id)*/
	
}

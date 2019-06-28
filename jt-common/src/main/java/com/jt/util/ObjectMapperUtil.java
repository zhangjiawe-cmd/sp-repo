package com.jt.util;

import com.fasterxml.jackson.databind.ObjectMapper;

//编辑工具类实现对象与json转化
public class ObjectMapperUtil {
	private static final ObjectMapper mapper=new ObjectMapper();
	public static String toJson(Object target) {
		String json = null;
		try {
			json = mapper.writeValueAsString(target);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return json;
	}
	public static <T>T toObject(String json ,Class<T> targetClass) {
		T target=null;
		try {
			target=mapper.readValue(json, targetClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return target;
	}
}

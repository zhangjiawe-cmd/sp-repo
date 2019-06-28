package com.jt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.jt.enu.KEY_ENUM;

@Retention(RetentionPolicy.RUNTIME)//这个注解运行时有效
@Target({ElementType.METHOD,ElementType.FIELD})//这个注解修饰方法时有效
public @interface RequiredCache {
	String key() default "";//接受用户的key的值
	KEY_ENUM keyType() default KEY_ENUM.AUTO;//定义key类型
	int secondes() default 0;//永不失效
}

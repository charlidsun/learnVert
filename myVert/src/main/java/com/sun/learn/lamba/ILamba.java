package com.sun.learn.lamba;

import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 功能：
 * 说明：
 * @author 孙荆阁:
 * @Date 2018年8月1日 上午9:18:17
 */
public interface ILamba {
	
	/**
	 * Predicate，接收一个参数，返回boolean
	 */
	Predicate<String> isNotEmptys = (s) -> s != null && s.trim().length()>0;
	Predicate<String> isEmptys = (s) -> s == null || s.trim().length()==0;
	
	Predicate<Boolean> isNotNull = Objects::nonNull;
	Predicate<Boolean> isNull = Objects::isNull;
	
	Predicate<String> isEmpty = String::isEmpty;
	Predicate<String> isNotEmpty = isEmpty.negate();

	/**
	 * Function 接收一个参数，返回一个结果
	 */
	Function<Integer, Integer> mathAbs =  (val) -> Math.abs(val);
	Function<Integer,String> toString = String :: valueOf;
	Function<Integer, String> backToString = toString.andThen(String::valueOf);
	
	/**
	 * Supplier 没有传入参数，返回任意范围
	 */
	Supplier<Double> myRandom = () -> Math.random();
	Supplier<String> uuId = ()->UUID.randomUUID().toString().replace("-", "");
	
	/**
	 * Consumer 执行在单个参数上的操作
	 * 打印一些实体信息
	 */
	Consumer<String> oper = (val) -> Math.random();
	
	/**
	 * Comparator 比较数
	 * 前者大于后者返回1，相等返回0，否则返回-1
	 */
	Comparator<Integer> compar = (oneV,twoV) -> oneV.compareTo(twoV);
}

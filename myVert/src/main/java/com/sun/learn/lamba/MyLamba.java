package com.sun.learn.lamba;

import java.util.Arrays;
import java.util.List;

/**
 * 功能：
 * 说明：
 * @author 孙荆阁:
 * @Date 2018年7月31日 下午5:35:22
 */

public class MyLamba {

	public static void main(String[] args) {
		ICount iCount = (a,b) ->{
			return a+b;
		};		
		System.out.println(iCount.add(3, 4));
		
		List<String> list = Arrays.asList("1","2","3","4");
//		list.forEach(new Consumer<String>() {
//			@Override
//			public void accept(String t) {
//				System.out.println(t);
//			}
//		});
		//list.forEach(n->System.err.println(n));
		list.forEach(System.out::println);
	}
}

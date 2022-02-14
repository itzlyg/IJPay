package com.ijpay.core;

import com.ijpay.core.kit.HttpKit;

/**
 * @author xyb
 * @Description
 * @Date 2022/2/14 20:13
 */
public class Test {

	public static void main(String[] args) {
		String url = "https://www.baidu.com/";
		String out = HttpKit.get(url, String.class);
		System.out.println(out);
	}


}

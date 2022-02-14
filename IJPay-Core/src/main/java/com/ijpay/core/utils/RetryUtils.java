package com.ijpay.core.utils;

import java.util.concurrent.Callable;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNWX</p>
 *
 * <p>异常重试工具 by L.cm</p>
 *
 * @author Javen
 */
public class RetryUtils {

	/**
	 * 回调结果检查
	 */
	public interface ResultCheck {
		/**
		 * 是否匹配
		 *
		 * @return 匹配结果
		 */
		boolean matching();

		/**
		 * 获取 JSON
		 *
		 * @return json
		 */
		String getJson();
	}

	/**
	 * 在遇到异常时尝试重试
	 *
	 * @param retryLimit    重试次数
	 * @param retryCallable 重试回调
	 * @param <V>           泛型
	 * @return V 结果
	 */
	public static <V extends ResultCheck> V retryOnException(int retryLimit, Callable<V> retryCallable) {
		V v = null;
		for (int i = 0; i < retryLimit; i++) {
			try {
				v = retryCallable.call();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null != v && v.matching()) {
				break;
			}
		}
		return v;
	}

	/**
	 * 在遇到异常时尝试重试
	 *
	 * @param retryLimit    重试次数
	 * @param sleepMillis   每次重试之后休眠的时间
	 * @param retryCallable 重试回调
	 * @param <V>           泛型
	 * @return V 结果
	 * @throws java.lang.InterruptedException 线程异常
	 */
	public static <V extends ResultCheck> V retryOnException(int retryLimit, long sleepMillis, Callable<V> retryCallable) throws InterruptedException {
		V v = null;
		for (int i = 0; i < retryLimit; i++) {
			try {
				v = retryCallable.call();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null != v && v.matching()) {
				break;
			}
			if (sleepMillis > 0) {
				Thread.sleep(sleepMillis);
			}
		}
		return v;
	}
}

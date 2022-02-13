package com.ijpay.wxpay.model.v3;


/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNWX</p>
 *
 * <p>V3 统一下单-场景信息</p>
 *
 * @author Javen
 */
public class SceneInfo {
    /**
     * 用户终端IP
     */
    private String payer_client_ip;
    /**
     * 商户端设备号
     */
    private String device_id;
    /**
     * 商户门店信息
     */
    private StoreInfo store_info;
    /**
     * H5 场景信息
     */
    private H5Info h5_info;

	public String getPayer_client_ip() {
		return payer_client_ip;
	}

	public void setPayer_client_ip(String payer_client_ip) {
		this.payer_client_ip = payer_client_ip;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public StoreInfo getStore_info() {
		return store_info;
	}

	public void setStore_info(StoreInfo store_info) {
		this.store_info = store_info;
	}

	public H5Info getH5_info() {
		return h5_info;
	}

	public void setH5_info(H5Info h5_info) {
		this.h5_info = h5_info;
	}
}

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNWX</p>
 *
 * <p>云闪付-商户进件</p>
 *
 * @author Javen
 */
package com.ijpay.unionpay.model;


import com.ijpay.core.model.BaseModel;

public class ApplyModel extends BaseModel {
    /**
     * 合作伙伴 ID 即机构号
     */
    private String partner;
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 支持 MD5 和RSA，默认为MD5
     */
    private String signType;
    /**
     * 字符集，默认为UTF-8
     */
    private String charset;
    /**
     * 请求数据
     */
    private String data;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 数据签名
     */
    private String dataSign;

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataSign() {
		return dataSign;
	}

	public void setDataSign(String dataSign) {
		this.dataSign = dataSign;
	}
}

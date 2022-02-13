package com.ijpay.wxpay.model;

import com.ijpay.core.model.BaseModel;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付等常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNWX</p>
 *
 * <p>报关接口-订单附加信息提交/查询/重推 Model</p>
 *
 * @author Javen
 */
public class CustomDeclareModel extends BaseModel {
    private String sign;
    private String sign_type;
    private String appid;
    private String mch_id;
    private String out_trade_no;
    private String transaction_id;
    private String customs;
    private String mch_customs_no;
    private String duty;
    private String action_type;
    private String sub_order_no;
    private String sub_order_id;
    private String fee_type;
    private String order_fee;
    private String transport_fee;
    private String product_fee;
    private String cert_type;
    private String cert_id;
    private String name;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getCustoms() {
		return customs;
	}

	public void setCustoms(String customs) {
		this.customs = customs;
	}

	public String getMch_customs_no() {
		return mch_customs_no;
	}

	public void setMch_customs_no(String mch_customs_no) {
		this.mch_customs_no = mch_customs_no;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getAction_type() {
		return action_type;
	}

	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}

	public String getSub_order_no() {
		return sub_order_no;
	}

	public void setSub_order_no(String sub_order_no) {
		this.sub_order_no = sub_order_no;
	}

	public String getSub_order_id() {
		return sub_order_id;
	}

	public void setSub_order_id(String sub_order_id) {
		this.sub_order_id = sub_order_id;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getOrder_fee() {
		return order_fee;
	}

	public void setOrder_fee(String order_fee) {
		this.order_fee = order_fee;
	}

	public String getTransport_fee() {
		return transport_fee;
	}

	public void setTransport_fee(String transport_fee) {
		this.transport_fee = transport_fee;
	}

	public String getProduct_fee() {
		return product_fee;
	}

	public void setProduct_fee(String product_fee) {
		this.product_fee = product_fee;
	}

	public String getCert_type() {
		return cert_type;
	}

	public void setCert_type(String cert_type) {
		this.cert_type = cert_type;
	}

	public String getCert_id() {
		return cert_id;
	}

	public void setCert_id(String cert_id) {
		this.cert_id = cert_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

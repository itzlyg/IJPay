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
 * <p>分账回退 Model</p>
 *
 * @author Javen
 */
public class ProfitSharingReturn extends BaseModel {
    private String appid;
    private String sub_appid;
    private String mch_id;
    private String sub_mch_id;
    private String nonce_str;
    private String sign;
    private String sign_type;
    private String order_id;
    private String out_order_no;
    private String out_return_no;
    private String return_account_type;
    private String return_account;
    private String return_amount;
    private String description;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSub_appid() {
		return sub_appid;
	}

	public void setSub_appid(String sub_appid) {
		this.sub_appid = sub_appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getSub_mch_id() {
		return sub_mch_id;
	}

	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

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

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getOut_order_no() {
		return out_order_no;
	}

	public void setOut_order_no(String out_order_no) {
		this.out_order_no = out_order_no;
	}

	public String getOut_return_no() {
		return out_return_no;
	}

	public void setOut_return_no(String out_return_no) {
		this.out_return_no = out_return_no;
	}

	public String getReturn_account_type() {
		return return_account_type;
	}

	public void setReturn_account_type(String return_account_type) {
		this.return_account_type = return_account_type;
	}

	public String getReturn_account() {
		return return_account;
	}

	public void setReturn_account(String return_account) {
		this.return_account = return_account;
	}

	public String getReturn_amount() {
		return return_amount;
	}

	public void setReturn_amount(String return_amount) {
		this.return_amount = return_amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

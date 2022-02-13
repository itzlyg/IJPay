package com.ijpay.qqpay.model;

import com.ijpay.core.model.BaseModel;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNWX</p>
 *
 * <p>创建现金红包 Model</p>
 *
 * @author Javen
 */
public class CreateHbModel extends BaseModel {
    private String charset;
    private String nonce_str;
    private String sign;
    private String mch_billno;
    private String mch_id;
    private String mch_name;
    private String qqappid;
    private String re_openid;
    private String total_amount;
    private String total_num;
    private String wishing;
    private String act_name;
    private String icon_id;
    private String banner_id;
    private String notify_url;
    private String not_send_msg;
    private String min_value;
    private String max_value;

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
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

	public String getMch_billno() {
		return mch_billno;
	}

	public void setMch_billno(String mch_billno) {
		this.mch_billno = mch_billno;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getMch_name() {
		return mch_name;
	}

	public void setMch_name(String mch_name) {
		this.mch_name = mch_name;
	}

	public String getQqappid() {
		return qqappid;
	}

	public void setQqappid(String qqappid) {
		this.qqappid = qqappid;
	}

	public String getRe_openid() {
		return re_openid;
	}

	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getTotal_num() {
		return total_num;
	}

	public void setTotal_num(String total_num) {
		this.total_num = total_num;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public String getIcon_id() {
		return icon_id;
	}

	public void setIcon_id(String icon_id) {
		this.icon_id = icon_id;
	}

	public String getBanner_id() {
		return banner_id;
	}

	public void setBanner_id(String banner_id) {
		this.banner_id = banner_id;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getNot_send_msg() {
		return not_send_msg;
	}

	public void setNot_send_msg(String not_send_msg) {
		this.not_send_msg = not_send_msg;
	}

	public String getMin_value() {
		return min_value;
	}

	public void setMin_value(String min_value) {
		this.min_value = min_value;
	}

	public String getMax_value() {
		return max_value;
	}

	public void setMax_value(String max_value) {
		this.max_value = max_value;
	}
}

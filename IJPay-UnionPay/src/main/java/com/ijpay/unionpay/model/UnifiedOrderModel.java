/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNWX</p>
 *
 * <p>云闪付-统一下单</p>
 *
 * @author Javen
 */
package com.ijpay.unionpay.model;
import com.ijpay.core.model.BaseModel;

public class UnifiedOrderModel extends BaseModel {
    private String service;
    private String version;
    private String charset;
    private String sign_type;
    private String mch_id;
    private String appid;
    private String is_raw;
    private String is_minipg;
    private String out_trade_no;
    private String device_info;
    private String op_shop_id;
    private String body;
    private String sub_openid;
    private String user_id;
    private String attach;
    private String sub_appid;
    private String total_fee;
    private String need_receipt;
    private String customer_ip;
    private String mch_create_ip;
    private String notify_url;
    private String time_start;
    private String time_expire;
    private String qr_code_timeout_express;
    private String op_user_id;
    private String goods_tag;
    private String product_id;
    private String nonce_str;
    private String buyer_logon_id;
    private String buyer_id;
    private String limit_credit_pay;
    private String sign;
    private String sign_agentno;
    private String groupno;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getIs_raw() {
		return is_raw;
	}

	public void setIs_raw(String is_raw) {
		this.is_raw = is_raw;
	}

	public String getIs_minipg() {
		return is_minipg;
	}

	public void setIs_minipg(String is_minipg) {
		this.is_minipg = is_minipg;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getOp_shop_id() {
		return op_shop_id;
	}

	public void setOp_shop_id(String op_shop_id) {
		this.op_shop_id = op_shop_id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSub_openid() {
		return sub_openid;
	}

	public void setSub_openid(String sub_openid) {
		this.sub_openid = sub_openid;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getSub_appid() {
		return sub_appid;
	}

	public void setSub_appid(String sub_appid) {
		this.sub_appid = sub_appid;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getNeed_receipt() {
		return need_receipt;
	}

	public void setNeed_receipt(String need_receipt) {
		this.need_receipt = need_receipt;
	}

	public String getCustomer_ip() {
		return customer_ip;
	}

	public void setCustomer_ip(String customer_ip) {
		this.customer_ip = customer_ip;
	}

	public String getMch_create_ip() {
		return mch_create_ip;
	}

	public void setMch_create_ip(String mch_create_ip) {
		this.mch_create_ip = mch_create_ip;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getQr_code_timeout_express() {
		return qr_code_timeout_express;
	}

	public void setQr_code_timeout_express(String qr_code_timeout_express) {
		this.qr_code_timeout_express = qr_code_timeout_express;
	}

	public String getOp_user_id() {
		return op_user_id;
	}

	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getBuyer_logon_id() {
		return buyer_logon_id;
	}

	public void setBuyer_logon_id(String buyer_logon_id) {
		this.buyer_logon_id = buyer_logon_id;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getLimit_credit_pay() {
		return limit_credit_pay;
	}

	public void setLimit_credit_pay(String limit_credit_pay) {
		this.limit_credit_pay = limit_credit_pay;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign_agentno() {
		return sign_agentno;
	}

	public void setSign_agentno(String sign_agentno) {
		this.sign_agentno = sign_agentno;
	}

	public String getGroupno() {
		return groupno;
	}

	public void setGroupno(String groupno) {
		this.groupno = groupno;
	}
}

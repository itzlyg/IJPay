package com.ijpay.wxpay.model.v3;

import java.util.List;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNWX</p>
 *
 * <p>V3 微信申请退款 Model</p>
 *
 * @author Javen
 */
public class RefundModel {
    /**
     * 微信支付订单号
     */
    private String transaction_id;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 商户退款单号
     */
    private String out_refund_no;
    /**
     * 退款原因
     */
    private String reason;
    /**
     * 退款结果回调url
     */
    private String notify_url;
    /**
     * 退款资金来源
     */
    private String funds_account;
    /**
     * 金额信息
     */
    private RefundAmount amount;
    /**
     * 退款商品
     */
    private List<RefundGoodsDetail> goods_detail;

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getFunds_account() {
		return funds_account;
	}

	public void setFunds_account(String funds_account) {
		this.funds_account = funds_account;
	}

	public RefundAmount getAmount() {
		return amount;
	}

	public void setAmount(RefundAmount amount) {
		this.amount = amount;
	}

	public List<RefundGoodsDetail> getGoods_detail() {
		return goods_detail;
	}

	public void setGoods_detail(List<RefundGoodsDetail> goods_detail) {
		this.goods_detail = goods_detail;
	}
}



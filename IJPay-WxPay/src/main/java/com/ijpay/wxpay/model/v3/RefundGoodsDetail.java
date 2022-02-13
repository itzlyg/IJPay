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
 * <p>V3 微信申请退款-退款商品</p>
 *
 * @author Javen
 */
public class RefundGoodsDetail {
    /**
     * 商户侧商品编码
     */
    private String merchant_goods_id;
    /**
     * 微信侧商品编码
     */
    private String wechatpay_goods_id;
    /**
     * 商品名称
     */
    private String goods_name;
    /**
     * 商品单价
     */
    private int unit_price;
    /**
     * 商品退款金额
     */
    private int refund_amount;
    /**
     * 商品退货数量
     */
    private int refund_quantity;

	public String getMerchant_goods_id() {
		return merchant_goods_id;
	}

	public void setMerchant_goods_id(String merchant_goods_id) {
		this.merchant_goods_id = merchant_goods_id;
	}

	public String getWechatpay_goods_id() {
		return wechatpay_goods_id;
	}

	public void setWechatpay_goods_id(String wechatpay_goods_id) {
		this.wechatpay_goods_id = wechatpay_goods_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public int getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(int unit_price) {
		this.unit_price = unit_price;
	}

	public int getRefund_amount() {
		return refund_amount;
	}

	public void setRefund_amount(int refund_amount) {
		this.refund_amount = refund_amount;
	}

	public int getRefund_quantity() {
		return refund_quantity;
	}

	public void setRefund_quantity(int refund_quantity) {
		this.refund_quantity = refund_quantity;
	}
}

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
 * <p>V3 统一下单 Model</p>
 *
 * @author Javen
 */
public class UnifiedOrderModel {
    /**
     * 公众号ID
     */
    private String appid;
    /**
     * 服务商公众号ID
     */
    private String sp_appid;
    /**
     * 直连商户号
     */
    private String mchid;
    /**
     * 服务商户号
     */
    private String sp_mchid;
    /**
     * 子商户公众号ID
     */
    private String sub_appid;
    /**
     * 子商户号
     */
    private String sub_mchid;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 交易结束时间
     */
    private String time_expire;
    /**
     * 附加数据
     */
    private String attach;
    /**
     * 通知地址
     */
    private String notify_url;
    /**
     * 订单优惠标记
     */
    private String goods_tag;
    /**
     * 结算信息
     */
    private SettleInfo settle_info;
    /**
     * 订单金额
     */
    private Amount amount;
    /**
     * 支付者
     */
    private Payer payer;
    /**
     * 优惠功能
     */
    private Detail detail;
    /**
     * 场景信息
     */
    private SceneInfo scene_info;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSp_appid() {
		return sp_appid;
	}

	public void setSp_appid(String sp_appid) {
		this.sp_appid = sp_appid;
	}

	public String getMchid() {
		return mchid;
	}

	public void setMchid(String mchid) {
		this.mchid = mchid;
	}

	public String getSp_mchid() {
		return sp_mchid;
	}

	public void setSp_mchid(String sp_mchid) {
		this.sp_mchid = sp_mchid;
	}

	public String getSub_appid() {
		return sub_appid;
	}

	public void setSub_appid(String sub_appid) {
		this.sub_appid = sub_appid;
	}

	public String getSub_mchid() {
		return sub_mchid;
	}

	public void setSub_mchid(String sub_mchid) {
		this.sub_mchid = sub_mchid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public SettleInfo getSettle_info() {
		return settle_info;
	}

	public void setSettle_info(SettleInfo settle_info) {
		this.settle_info = settle_info;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public Payer getPayer() {
		return payer;
	}

	public void setPayer(Payer payer) {
		this.payer = payer;
	}

	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	public SceneInfo getScene_info() {
		return scene_info;
	}

	public void setScene_info(SceneInfo scene_info) {
		this.scene_info = scene_info;
	}
}



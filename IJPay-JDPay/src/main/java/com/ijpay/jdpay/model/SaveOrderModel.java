/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNWX</p>
 *
 * <p> 在线支付接口 </p>
 *
 * @author Javen
 */
package com.ijpay.jdpay.model;


public class SaveOrderModel extends JdBaseModel {
   private String version;
   private String sign;
   private String merchant;
   private String payMerchant;
   private String device;
   private String tradeNum;
   private String tradeName;
   private String tradeDesc;
   private String tradeTime;
   private String amount;
   private String orderType;
   private String industryCategoryCode;
   private String currency;
   private String note;
   private String callbackUrl;
   private String notifyUrl;
   private String ip;
   private String specCardNo;
   private String specId;
   private String specName;
   private String userId;
   private String expireTime;
   private String orderGoodsNum;
   private String vendorId;
   private String goodsInfo;
   private String receiverInfo;
   private String termInfo;
   private String riskInfo;
   private String settleCurrency;
   private String kjInfo;
   private String installmentNum;
   private String preProduct;
   private String bizTp;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getPayMerchant() {
		return payMerchant;
	}

	public void setPayMerchant(String payMerchant) {
		this.payMerchant = payMerchant;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getTradeNum() {
		return tradeNum;
	}

	public void setTradeNum(String tradeNum) {
		this.tradeNum = tradeNum;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getTradeDesc() {
		return tradeDesc;
	}

	public void setTradeDesc(String tradeDesc) {
		this.tradeDesc = tradeDesc;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getIndustryCategoryCode() {
		return industryCategoryCode;
	}

	public void setIndustryCategoryCode(String industryCategoryCode) {
		this.industryCategoryCode = industryCategoryCode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSpecCardNo() {
		return specCardNo;
	}

	public void setSpecCardNo(String specCardNo) {
		this.specCardNo = specCardNo;
	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public String getOrderGoodsNum() {
		return orderGoodsNum;
	}

	public void setOrderGoodsNum(String orderGoodsNum) {
		this.orderGoodsNum = orderGoodsNum;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public String getReceiverInfo() {
		return receiverInfo;
	}

	public void setReceiverInfo(String receiverInfo) {
		this.receiverInfo = receiverInfo;
	}

	public String getTermInfo() {
		return termInfo;
	}

	public void setTermInfo(String termInfo) {
		this.termInfo = termInfo;
	}

	public String getRiskInfo() {
		return riskInfo;
	}

	public void setRiskInfo(String riskInfo) {
		this.riskInfo = riskInfo;
	}

	public String getSettleCurrency() {
		return settleCurrency;
	}

	public void setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
	}

	public String getKjInfo() {
		return kjInfo;
	}

	public void setKjInfo(String kjInfo) {
		this.kjInfo = kjInfo;
	}

	public String getInstallmentNum() {
		return installmentNum;
	}

	public void setInstallmentNum(String installmentNum) {
		this.installmentNum = installmentNum;
	}

	public String getPreProduct() {
		return preProduct;
	}

	public void setPreProduct(String preProduct) {
		this.preProduct = preProduct;
	}

	public String getBizTp() {
		return bizTp;
	}

	public void setBizTp(String bizTp) {
		this.bizTp = bizTp;
	}
}

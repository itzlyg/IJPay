package com.ijpay.qqpay;

import java.io.Serializable;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付等常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNWX</p>
 *
 * <p>QQ 钱包支付常用配置</p>
 *
 * @author Javen
 */
public class QqPayApiConfig implements Serializable {
    private static final long serialVersionUID = 8365822256469245771L;

    private String appId;
    private String mchId;
    private String slAppId;
    private String slMchId;
    private String partnerKey;
    private String domain;
    private String certPath;
    private Object exParams;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getSlAppId() {
		return slAppId;
	}

	public void setSlAppId(String slAppId) {
		this.slAppId = slAppId;
	}

	public String getSlMchId() {
		return slMchId;
	}

	public void setSlMchId(String slMchId) {
		this.slMchId = slMchId;
	}

	public String getPartnerKey() {
		return partnerKey;
	}

	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getCertPath() {
		return certPath;
	}

	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}

	public Object getExParams() {
		return exParams;
	}

	public void setExParams(Object exParams) {
		this.exParams = exParams;
	}
}

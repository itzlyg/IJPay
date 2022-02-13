package com.ijpay.wxpay;


import java.io.Serializable;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNWX</p>
 *
 * <p>微信支付常用配置</p>
 *
 * @author Javen
 */
public class WxPayApiConfig implements Serializable {
    private static final long serialVersionUID = -9044503427692786302L;
    /**
     * 应用编号
     */
    private String appId;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * 服务商应用编号
     */
    private String slAppId;
    /**
     * 服务商商户号
     */
    private String slMchId;
    /**
     * 同 apiKey 后续版本会舍弃
     */
    private String partnerKey;
    /**
     * 商户平台「API安全」中的 API 密钥
     */
    private String apiKey;
    /**
     * 商户平台「API安全」中的 APIv3 密钥
     */
    private String apiKey3;
    /**
     * 应用域名，回调中会使用此参数
     */
    private String domain;
    /**
     * API 证书中的 p12
     */
    private String certPath;
    /**
     * API 证书中的 key.pem
     */
    private String keyPemPath;
    /**
     * API 证书中的 cert.pem
     */
    private String certPemPath;
    /**
     * 其他附加参数
     */
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

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiKey3() {
		return apiKey3;
	}

	public void setApiKey3(String apiKey3) {
		this.apiKey3 = apiKey3;
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

	public String getKeyPemPath() {
		return keyPemPath;
	}

	public void setKeyPemPath(String keyPemPath) {
		this.keyPemPath = keyPemPath;
	}

	public String getCertPemPath() {
		return certPemPath;
	}

	public void setCertPemPath(String certPemPath) {
		this.certPemPath = certPemPath;
	}

	public Object getExParams() {
		return exParams;
	}

	public void setExParams(Object exParams) {
		this.exParams = exParams;
	}
}

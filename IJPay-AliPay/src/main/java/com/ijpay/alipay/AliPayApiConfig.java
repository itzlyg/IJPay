package com.ijpay.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNWXX</p>
 *
 * <p>支付宝支付配置</p>
 *
 * @author Javen
 */
public class AliPayApiConfig implements Serializable {
    private static final long serialVersionUID = -4736760736935998953L;

    private String privateKey;
    private String aliPayPublicKey;
    private String appId;
    private String serviceUrl;
    private String charset;
    private String signType;
    private String format;
    private boolean certModel;
    private String appCertPath;
    private String appCertContent;
    private String aliPayCertPath;
    private String aliPayCertContent;
    private String aliPayRootCertPath;
    private String aliPayRootCertContent;
    private AlipayClient alipayClient;

    private AliPayApiConfig() {
    }

    public static AliPayApiConfig builder() {
        return new AliPayApiConfig();
    }

    /**
     * 普通公钥方式
     *
     * @return AliPayApiConfig 支付宝配置
     */
    public AliPayApiConfig build() {
        this.alipayClient = new DefaultAlipayClient(getServiceUrl(), getAppId(), getPrivateKey(), getFormat(),
                getCharset(), getAliPayPublicKey(), getSignType());
        return this;
    }

    /**
     * 证书模式
     *
     * @return AliPayApiConfig 支付宝配置
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public AliPayApiConfig buildByCert() throws AlipayApiException {
        return build(getAppCertPath(), getAliPayCertPath(), getAliPayRootCertPath());
    }

    /**
     * 证书模式
     *
     * @return AliPayApiConfig 支付宝配置
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public AliPayApiConfig buildByCertContent() throws AlipayApiException {
        return buildByCertContent(getAppCertContent(), getAliPayCertContent(), getAliPayRootCertContent());
    }

    /**
     * @param appCertPath        应用公钥证书路径
     * @param aliPayCertPath     支付宝公钥证书文件路径
     * @param aliPayRootCertPath 支付宝CA根证书文件路径
     * @return {@link AliPayApiConfig}  支付宝支付配置
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public AliPayApiConfig build(String appCertPath, String aliPayCertPath, String aliPayRootCertPath) throws AlipayApiException {
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        certAlipayRequest.setServerUrl(getServiceUrl());
        certAlipayRequest.setAppId(getAppId());
        certAlipayRequest.setPrivateKey(getPrivateKey());
        certAlipayRequest.setFormat(getFormat());
        certAlipayRequest.setCharset(getCharset());
        certAlipayRequest.setSignType(getSignType());
        certAlipayRequest.setCertPath(appCertPath);
        certAlipayRequest.setAlipayPublicCertPath(aliPayCertPath);
        certAlipayRequest.setRootCertPath(aliPayRootCertPath);
        this.alipayClient = new DefaultAlipayClient(certAlipayRequest);
        this.certModel = true;
        return this;
    }

    /**
     * @param appCertContent        应用公钥证书
     * @param aliPayCertContent     支付宝公钥证书
     * @param aliPayRootCertContent 支付宝CA根证书
     * @return {@link AliPayApiConfig}  支付宝支付配置
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public AliPayApiConfig buildByCertContent(String appCertContent, String aliPayCertContent, String aliPayRootCertContent) throws AlipayApiException {
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        certAlipayRequest.setServerUrl(getServiceUrl());
        certAlipayRequest.setAppId(getAppId());
        certAlipayRequest.setPrivateKey(getPrivateKey());
        certAlipayRequest.setFormat(getFormat());
        certAlipayRequest.setCharset(getCharset());
        certAlipayRequest.setSignType(getSignType());
        certAlipayRequest.setCertContent(appCertContent);
        certAlipayRequest.setAlipayPublicCertContent(aliPayCertContent);
        certAlipayRequest.setRootCertContent(aliPayRootCertContent);
        this.alipayClient = new DefaultAlipayClient(certAlipayRequest);
        this.certModel = true;
        return this;
    }

    public String getPrivateKey() {
        if (StringUtils.isBlank(privateKey)) {
            throw new IllegalStateException("privateKey 未被赋值");
        }
        return privateKey;
    }

    public AliPayApiConfig setPrivateKey(String privateKey) {
        if (StringUtils.isEmpty(privateKey)) {
            throw new IllegalArgumentException("privateKey 值不能为 null");
        }
        this.privateKey = privateKey;
        return this;
    }

    public String getAliPayPublicKey() {
        return aliPayPublicKey;
    }

    public AliPayApiConfig setAliPayPublicKey(String aliPayPublicKey) {
        this.aliPayPublicKey = aliPayPublicKey;
        return this;
    }

    public String getAppId() {
        if (StringUtils.isEmpty(appId)) {
            throw new IllegalStateException("appId 未被赋值");
        }
        return appId;
    }

    public AliPayApiConfig setAppId(String appId) {
        if (StringUtils.isEmpty(appId)) {
            throw new IllegalArgumentException("appId 值不能为 null");
        }
        this.appId = appId;
        return this;
    }

    public String getServiceUrl() {
        if (StringUtils.isEmpty(serviceUrl)) {
            throw new IllegalStateException("serviceUrl 未被赋值");
        }
        return serviceUrl;
    }

    public AliPayApiConfig setServiceUrl(String serviceUrl) {
        if (StringUtils.isEmpty(serviceUrl)) {
            serviceUrl = "https://openapi.alipay.com/gateway.do";
        }
        this.serviceUrl = serviceUrl;
        return this;
    }

    public String getCharset() {
        if (StringUtils.isEmpty(charset)) {
            charset = "UTF-8";
        }
        return charset;
    }

    public AliPayApiConfig setCharset(String charset) {
        if (StringUtils.isEmpty(charset)) {
            charset = "UTF-8";
        }
        this.charset = charset;
        return this;
    }

    public String getSignType() {
        if (StringUtils.isEmpty(signType)) {
            signType = "RSA2";
        }
        return signType;
    }

    public AliPayApiConfig setSignType(String signType) {
        if (StringUtils.isEmpty(signType)) {
            signType = "RSA2";
        }
        this.signType = signType;
        return this;
    }

    public String getFormat() {
        if (StringUtils.isEmpty(format)) {
            format = "json";
        }
        return format;
    }

    public String getAppCertPath() {
        return appCertPath;
    }

    public AliPayApiConfig setAppCertPath(String appCertPath) {
        this.appCertPath = appCertPath;
        return this;
    }

    public String getAppCertContent() {
        return appCertContent;
    }

    public AliPayApiConfig setAppCertContent(String appCertContent) {
        this.appCertContent = appCertContent;
        return this;
    }

    public String getAliPayCertPath() {
        return aliPayCertPath;
    }

    public AliPayApiConfig setAliPayCertPath(String aliPayCertPath) {
        this.aliPayCertPath = aliPayCertPath;
        return this;
    }

    public String getAliPayCertContent() {
        return aliPayCertContent;
    }

    public AliPayApiConfig setAliPayCertContent(String aliPayCertContent) {
        this.aliPayCertContent = aliPayCertContent;
        return this;
    }

    public String getAliPayRootCertPath() {
        return aliPayRootCertPath;
    }

    public AliPayApiConfig setAliPayRootCertPath(String aliPayRootCertPath) {
        this.aliPayRootCertPath = aliPayRootCertPath;
        return this;
    }

    public String getAliPayRootCertContent() {
        return aliPayRootCertContent;
    }

    public AliPayApiConfig setAliPayRootCertContent(String aliPayRootCertContent) {
        this.aliPayRootCertContent = aliPayRootCertContent;
        return this;
    }

    public boolean isCertModel() {
        return certModel;
    }

    public AliPayApiConfig setCertModel(boolean certModel) {
        this.certModel = certModel;
        return this;
    }

    public AlipayClient getAliPayClient() {
        if (alipayClient == null) {
            throw new IllegalStateException("aliPayClient 未被初始化");
        }
        return alipayClient;
    }

}

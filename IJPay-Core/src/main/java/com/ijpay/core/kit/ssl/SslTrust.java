package com.ijpay.core.kit.ssl;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


/**
 * SslTrust
 * @Description
 * @Copyright Copyright (c) 2019
 * @author xyb
 * @Date 2019年12月18日 上午10:10:13
 *
 */
public class SslTrust implements X509TrustManager {

	@Override
	public void checkClientTrusted(X509Certificate[] certs, String s) throws CertificateException {

	}

	@Override
	public void checkServerTrusted(X509Certificate[] certs, String s) throws CertificateException {

	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}

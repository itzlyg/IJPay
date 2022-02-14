package com.ijpay.core.kit.ssl;

import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.net.HttpURLConnection;
import java.security.SecureRandom;

/**
 * RestTemplate 调用 https
 * @Description
 * @Copyright Copyright (c) 2019
 * @author xyb
 * @Date 2019年12月18日 上午10:09:46
 *
 */
public class SslRequest extends SimpleClientHttpRequestFactory {

	@Override
	protected void prepareConnection(HttpURLConnection connection, String httpMethod) {
		if (!(connection instanceof HttpsURLConnection)) {
			throw new RuntimeException("An instance of HttpsURLConnection is expected");
		}
		HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;
		TrustManager[] tms = new TrustManager[] {new SslTrust()};
		try {
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, tms, new SecureRandom());
			httpsConnection.setSSLSocketFactory(new SslSocket(context.getSocketFactory()));
			httpsConnection.setHostnameVerifier((s, ssl) -> true);
			super.prepareConnection(httpsConnection, httpMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

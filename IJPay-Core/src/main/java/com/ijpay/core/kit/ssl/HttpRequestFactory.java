package com.ijpay.core.kit.ssl;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * RestTemplate 调用 https 使用证书
 *
 * @author xyb
 * @Description
 * @Copyright Copyright (c) 2019
 * @Date 2020年06月28日 上午10:09:46
 */
public class HttpRequestFactory extends HttpComponentsClientHttpRequestFactory {

	private static final String TLSV1 = "TLSv1";

	private HttpRequestFactory() {
	}

	public static HttpRequestFactory factory() {
		return new HttpRequestFactory();
	}

	public static HttpRequestFactory certFactory(InputStream is, String certPath, String protocols) {
		HttpRequestFactory factory = new HttpRequestFactory();
		if (protocols == null) {
			protocols = TLSV1;
		}
		String[] supportedProtocols = new String[]{protocols};
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			char[] midArr = certPath.toCharArray();
			keyStore.load(is, midArr);
			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContextBuilder.create()
				.loadKeyMaterial(keyStore, midArr)
				.build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, supportedProtocols,
				null, NoopHostnameVerifier.INSTANCE);
			CloseableHttpClient httpclient = HttpClients.custom()
				.setSSLSocketFactory(sslsf)
				.build();
			factory.setHttpClient(httpclient);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return factory;
	}

}

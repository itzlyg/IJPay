package com.ijpay.core.kit;

import com.fasterxml.jackson.databind.JsonNode;
import com.ijpay.core.IJPayHttpResponse;
import com.ijpay.core.kit.ssl.HttpRequestFactory;
import com.ijpay.core.kit.ssl.SslRequest;
import com.ijpay.core.utils.PayJsonUtil;
import org.apache.commons.collections4.MapUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>IJPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>IJPay 交流群: 723992875</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNWX</p>
 *
 * <p>Http 工具类</p>
 *
 * @author Javen
 */
public class HttpKit {

	/** https请求 */
	private static final String HTTPS = "https";

	/** 普通https请求 */
	private static RestTemplate httpsRest = new RestTemplate(new SslRequest());

	private static RestTemplate rest = new RestTemplate(HttpRequestFactory.factory());

	/**
	 *  无参数的get请求
	 * @Description
	 * @Date 2019年12月18日 上午10:22:02
	 * @param url 地址
	 * @param clazz 返参类型
	 * @return 返回值
	 * @throws Exception
	 */
	public static <T> T get(String url, Class<T> clazz) {
		T t = null;
		URI uri = uri(url);
		try {
			t = getRest(url).getForObject(uri, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}


	/**
	 * get 请求
	 *
	 * @param url      请求url
	 * @param paramMap 请求参数
	 * @return {@link String} 请求返回的结果
	 */
	public static String get(String url, Map<String, Object> paramMap) {
		String t = null;
		try {
			t = getRest(url).getForObject(url, String.class, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * get 请求
	 *
	 * @param url      请求url
	 * @param paramMap 请求参数
	 * @param headers  请求头
	 * @return {@link IJPayHttpResponse} 请求返回的结果
	 */
	public static IJPayHttpResponse get(String url, Map<String, Object> paramMap, Map<String, String> headers) {
		ResponseEntity<String> out = exchange(HttpMethod.GET, url, paramMap, headers, String.class);
		return response(out);
	}


	/***
	 *  form表单post请求
	 * @author: xyb
	 * @date: 2020-08-24 22:07:16
	 * @param url 地址
	 * @param t 参数对象
	 * @param clazz 返回结果class
	 * @return: R 返回结果
	 */
	public static <T extends Serializable, R> R formPost (String url, @Nullable T t, Class<R> clazz){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		JsonNode node = PayJsonUtil.toNode(t);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>(64);
		if (node.isObject()) {
			Iterator<Map.Entry<String, JsonNode>> it = node.fields();
			Map.Entry<String, JsonNode> entry;
			while (it.hasNext()) {
				entry = it.next();
				params.add(entry.getKey(), entry.getValue().textValue());
			}
		}
		//提交请求
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
		return postForObject(url, entity, clazz);
	}
	/**
	 * post 请求
	 *
	 * @param url  请求url
	 * @param data 请求参数
	 * @return {@link String} 请求返回的结果
	 */
	public static String post(String url, String data) {
		return post(url, data, String.class);
	}

	/**
	 * POST 请求
	 * @Description
	 * @Date 2019年12月18日 上午10:22:40
	 * @param url 地址
	 * @param params 参数
	 * @param clazz 返参类型
	 * @return 返回值
	 * @throws Exception
	 */
	public static <T> T post (String url, @Nullable Object params, Class<T> clazz) {
		return postForObject(url, params, clazz);
	}


	public static RestTemplate getRest (String url){
		if (url.startsWith(HTTPS)) {
			return httpsRest;
		} else {
			return rest;
		}
	}
	/**
	 * post 请求
	 *
	 * @param url     请求url
	 * @param paramMap    请求参数
	 * @param headers 请求头
	 * @return {@link IJPayHttpResponse}  请求返回的结果
	 */
	public static IJPayHttpResponse post(String url,  Map<String, Object> paramMap, Map<String, String> headers) {
		ResponseEntity<String> out = exchange(HttpMethod.POST, url, paramMap, headers, String.class);
		return response(out);
	}

	/**
	 * post 请求
	 *
	 * @param url     请求url
	 * @param data    请求参数
	 * @param headers 请求头
	 * @return {@link IJPayHttpResponse}  请求返回的结果
	 */
	public static IJPayHttpResponse post(String url, String data, Map<String, String> headers) {
		return exchange(HttpMethod.POST, url, data, headers);
	}

	/**
	 * post 请求
	 *
	 * @param url      请求url
	 * @param data     请求参数
	 * @param certPath 证书路径
	 * @param certPass 证书密码
	 * @return {@link String} 请求返回的结果
	 */
	public static String post(String url, String data, String certPath, String certPass) {
		return post(url, data, certPath, certPass, null);
	}

	/**
	 * post 请求
	 *
	 * @param url      请求url
	 * @param data     请求参数
	 * @param certFile 证书文件输入流
	 * @param certPass 证书密码
	 * @return {@link String} 请求返回的结果
	 */
	public static String post(String url, String data, InputStream certFile, String certPass) {
		return post(url, data, certFile, certPass, null);
	}


	public static String post(String url, String data, String certPath, String certPass, String protocol) {
		try {
			FileInputStream is = new FileInputStream(certPath);
			return post(url, data, is, certPass, protocol);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String post(String url, String data, InputStream certFile, String certPass, String protocol) {
		HttpRequestFactory factory = HttpRequestFactory.certFactory(certFile, certPass, protocol);
		RestTemplate restTemplate = new RestTemplate(factory);
		return postForObject(restTemplate, url, data, String.class);
	}


	public static IJPayHttpResponse patch(String url, String data, Map<String, String> headers) {
		return exchange(HttpMethod.PATCH, url, data, headers);
	}

	public static IJPayHttpResponse put(String url, String data, Map<String, String> headers) {
		return exchange(HttpMethod.PUT, url, data, headers);
	}

	public static IJPayHttpResponse delete(String url, String data, Map<String, String> headers) {
		return exchange(HttpMethod.DELETE, url, data, headers);
	}

	public static String upload(String url, String data, String certPath, String certPass, String filePath, String protocol){
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "multipart/form-data;boundary=\"boundary\"");
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("file", new File(filePath));
		params.add("meta", data);
		HttpEntity<String> entity = new HttpEntity<>(data, headers);
		try {
			FileInputStream is = new FileInputStream(certPath);
			HttpRequestFactory factory = HttpRequestFactory.certFactory(is, certPass, protocol);
			RestTemplate restTemplate = new RestTemplate(factory);
			return postForObject(restTemplate, url, entity, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static <T> T postForObject (String url, @Nullable Object request, Class<T> responseType){
		return postForObject(null, url, request, responseType);
	}

	private static <T> T postForObject (RestTemplate rest, String url, @Nullable Object request, Class<T> responseType){
		URI uri = uri(url);
		try {
			if (rest == null) {
				rest = getRest(url);
			}
			return rest.postForObject(uri, request, responseType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static IJPayHttpResponse exchange(HttpMethod method, String url, String data, Map<String, String> headers) {
		HttpHeaders httpHeaders = httpHeaders(headers);
		HttpEntity<String> entity = new HttpEntity<>(data, httpHeaders);
		URI uri = uri(url);
		ResponseEntity<String> out = getRest(url).exchange(uri, method, entity, String.class);
		return response(out);
	}

	private static <T> ResponseEntity<T> exchange(HttpMethod method, String url, Map<String, Object> paramMap, Map<String, String> headers, Class<T> responseType){
		HttpHeaders httpHeaders = httpHeaders(headers);
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>(64);
		if (MapUtils.isNotEmpty(paramMap)) {
			for (Map.Entry<String, Object> e : paramMap.entrySet()) {
				params.add(e.getKey(), e.getValue());
			}
		}
		//提交请求
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(params, httpHeaders);
		URI uri = uri(url);
		ResponseEntity<T> out = getRest(url).exchange(uri, method, entity, responseType);
		return out;
	}

	private static HttpHeaders httpHeaders (Map<String, String> headers){
		HttpHeaders httpHeaders = new HttpHeaders();
		if (MapUtils.isNotEmpty(headers)) {
			for (Map.Entry<String, String> e : headers.entrySet()) {
				httpHeaders.add(e.getKey(), e.getValue());
			}
		}
		return httpHeaders;
	}

	private static IJPayHttpResponse response (ResponseEntity<String> out){
		IJPayHttpResponse response = new IJPayHttpResponse();
		response.setBody(out.getBody());
		response.setStatus(out.getStatusCodeValue());
		response.setHeaders(out.getHeaders());
		return response;
	}
	/**
	 * 	转uri
	 * @Description
	 * @Date 2019年6月4日 上午9:34:41
	 * @param url 地址
	 * @return uri
	 */
	private static URI uri (String url) {
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uri;
	}


	public static String readData(HttpServletRequest request) {
        BufferedReader br = null;
        try {
            StringBuilder result = new StringBuilder();
            br = request.getReader();
            for (String line; (line = br.readLine()) != null; ) {
                if (result.length() > 0) {
                    result.append("\n");
                }
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
